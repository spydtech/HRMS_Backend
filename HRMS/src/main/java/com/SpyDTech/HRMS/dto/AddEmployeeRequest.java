package com.SpyDTech.HRMS.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddEmployeeRequest {

    private String name;

    private String employee_id;

    private String email_id;

    private String phone_number;

    private String join_date;

    private String role;

}
