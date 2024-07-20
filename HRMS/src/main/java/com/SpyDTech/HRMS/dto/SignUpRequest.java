package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.Role;
import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String employeeid;

    private String mobileno;

    private String username;

    private String password;

    private Role role;

    public SignUpRequest(String firstName, String lastName, String email, String employeeid, String mobileno, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employeeid = employeeid;
        this.mobileno = mobileno;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
