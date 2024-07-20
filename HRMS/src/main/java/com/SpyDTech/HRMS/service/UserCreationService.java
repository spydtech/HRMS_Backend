package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.dto.SignUpResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserCreationService {

    ResponseEntity addUser(SignUpRequest signUpRequest);

    ResponseEntity getUsers();

    ResponseEntity getUser(String email);
}
