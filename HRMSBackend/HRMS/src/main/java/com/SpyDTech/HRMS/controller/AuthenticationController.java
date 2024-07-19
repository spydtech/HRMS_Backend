package com.SpyDTech.HRMS.controller;


import com.SpyDTech.HRMS.dto.JwtAuthenticationResponse;
import com.SpyDTech.HRMS.dto.RefreshTokenRequest;
import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.dto.SigninRequest;
import com.SpyDTech.HRMS.entities.User;
import com.SpyDTech.HRMS.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){

        return ResponseEntity.ok(authenticationService.signup(signUpRequest));

    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody SigninRequest signinRequest) throws IllegalAccessException {
        return  ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse>refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) throws IllegalAccessException {
        return  ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
