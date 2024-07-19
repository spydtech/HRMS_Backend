package com.SpyDTech.HRMS.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/edit")
@RequiredArgsConstructor
public class UserController {


    @GetMapping
    public ResponseEntity<String> sayHello(){

        return ResponseEntity.ok("Hi User ");
    }
}
