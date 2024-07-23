package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.OTPRequest;
import com.SpyDTech.HRMS.service.impl.ForgotPasswordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForgotPasswordController {


    @Autowired
    private ForgotPasswordServiceImpl service;


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPass(@RequestParam String email) {
        String response = service.forgotPass(email);

        if (response.startsWith("Invalid")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok("Otp sent to your email.");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPass(@RequestBody OTPRequest otpRequest) {
        String response = service.resetPass(otpRequest);

        if (response.equals("Invalid token") || response.equals("Token expired.")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam String newPassword) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        String response = service.updatePassword(username, newPassword);

        if (response.equals("User not found.")) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}
