package com.SpyDTech.HRMS.service.impl;


import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.dto.SignUpResponse;
import com.SpyDTech.HRMS.entities.User;
import com.SpyDTech.HRMS.repository.AllEmployeeRepository;
import com.SpyDTech.HRMS.repository.UserRepository;
import com.SpyDTech.HRMS.service.AuthenticationService;
import com.SpyDTech.HRMS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {

        return new UserDetailsService() {
            @Bean
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }






}
