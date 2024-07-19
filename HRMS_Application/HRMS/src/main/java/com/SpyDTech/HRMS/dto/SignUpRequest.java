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
}
