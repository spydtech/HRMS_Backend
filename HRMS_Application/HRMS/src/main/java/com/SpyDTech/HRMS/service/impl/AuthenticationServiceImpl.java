package com.SpyDTech.HRMS.service.impl;


import com.SpyDTech.HRMS.dto.JwtAuthenticationResponse;
import com.SpyDTech.HRMS.dto.RefreshTokenRequest;
import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.dto.SigninRequest;
import com.SpyDTech.HRMS.entities.Role;
import com.SpyDTech.HRMS.entities.User;
import com.SpyDTech.HRMS.repository.UserRepository;
import com.SpyDTech.HRMS.service.AuthenticationService;
import com.SpyDTech.HRMS.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();

        user.setFristname(signUpRequest.getFirstName());
        user.setSecondname(signUpRequest.getLastName());
        user.setEmployeeid(signUpRequest.getEmployeeid());
        user.setMobileno(signUpRequest.getMobileno());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setStatus(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest) throws IllegalAccessException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (signinRequest.getEmail(),signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(()-> new IllegalAccessException("Invalid email or password"));

        var jwt =jwtService.generateToken(user);

        var refreshToken =jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse =new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);

        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse =new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);

            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;

        }
        return null;

    }


}
