package com.SpyDTech.HRMS.service;


import com.SpyDTech.HRMS.dto.JwtAuthenticationResponse;
import com.SpyDTech.HRMS.dto.RefreshTokenRequest;
import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.dto.SigninRequest;
import com.SpyDTech.HRMS.entities.User;

public interface AuthenticationService {

     User signup(SignUpRequest signUpRequest);

     JwtAuthenticationResponse signin(SigninRequest signinRequest) throws IllegalAccessException;

     JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
