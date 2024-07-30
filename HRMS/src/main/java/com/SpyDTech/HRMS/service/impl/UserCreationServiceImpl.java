package com.SpyDTech.HRMS.service.impl;


import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.dto.SignUpResponse;
import com.SpyDTech.HRMS.entities.User;
import com.SpyDTech.HRMS.repository.AllEmployeeRepository;
import com.SpyDTech.HRMS.repository.UserRepository;
import com.SpyDTech.HRMS.service.AuthenticationService;
import com.SpyDTech.HRMS.service.UserCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCreationServiceImpl implements UserCreationService {

    private final UserRepository userRepository;

    private final AllEmployeeRepository allEmployeeRepository;

    private final AuthenticationService authenticationService;

    SignUpResponse response;

    @Override
    public ResponseEntity addUser(SignUpRequest signUpRequest) {

        if (allEmployeeRepository.existsByEmailId(signUpRequest.getEmail()) &&
                allEmployeeRepository.existsByEmployeeId(signUpRequest.getEmployeeid())) {

            User currentUser = authenticationService.signup(signUpRequest);
            SignUpResponse response = sendResponse(currentUser);
            return ResponseEntity.ok(response);
        }
        else if (userRepository.existsByEmail(signUpRequest.getEmail())) {

            ErrorResponse errorResponse = new ErrorResponse("Employee is already Registered");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }  else {

            ErrorResponse errorResponse = new ErrorResponse("Employee Email or Employee Id is incorrect");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }


    }

    @Override
    public ResponseEntity getUsers() {
        List<User> userList = userRepository.findAll();

        return ResponseEntity.ok(mappingToSignupResponse(userList));
    }

    @Override
    public ResponseEntity getUser(String email) {
        Optional<User> userList = userRepository.findByEmail(email);
        return ResponseEntity.ok(mappingToSignupResponse((userList.stream().toList())));
    }

    @Override
    public int getAllUsersCount() {
        return userRepository.findAll().size();
    }


    public List<SignUpResponse> mappingToSignupResponse(List<User> userList) {
        return userList.stream().map(user -> new SignUpResponse(
                user.getFristname(),
                user.getSecondname(),
                user.getEmail(),
                user.getEmployeeid(),
                user.getMobileno(),
                user.getUsername(),
                user.getStatus().toString()
               )).collect(Collectors.toList());
}


    public SignUpResponse sendResponse(User currentUser){

        SignUpResponse returnResponse = new SignUpResponse();
        returnResponse.setFirstName(currentUser.getFristname());
        returnResponse.setLastName(currentUser.getSecondname());
        returnResponse.setEmail(currentUser.getEmail());
        returnResponse.setMobileno(currentUser.getMobileno());
        returnResponse.setUsername(currentUser.getUsername());
        returnResponse.setEmployeeid(currentUser.getEmployeeid());
        returnResponse.setRole((currentUser.getStatus().toString()));
        return returnResponse;
    }
}
