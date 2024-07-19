package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.AddEmployeeRequest;
import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.service.UserCreationService;
import com.SpyDTech.HRMS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserCreationService userCreationService;

    @PostMapping("/create/addUser")
    public ResponseEntity saveEmployees(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(userCreationService.addUser(signUpRequest));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userCreationService.getUsers());
    }


}
