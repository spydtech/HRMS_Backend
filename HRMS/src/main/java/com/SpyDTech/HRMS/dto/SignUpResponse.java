package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignUpResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String employeeid;

    private String mobileno;

    private String username;

    private String role;

    public SignUpResponse(String firstName, String lastName, String email, String employeeid, String mobileno, String username, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employeeid = employeeid;
        this.mobileno = mobileno;
        this.username = username;
        this.role = role;
    }
}