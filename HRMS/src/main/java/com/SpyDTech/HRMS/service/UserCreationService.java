package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface UserCreationService {

    ResponseEntity addUser(SignUpRequest signUpRequest);

    ResponseEntity getUsers();

    ResponseEntity getUser(String email);

    int getAllUsersCount();
}
