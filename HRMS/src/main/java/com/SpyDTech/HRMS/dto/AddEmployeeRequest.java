package com.SpyDTech.HRMS.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddEmployeeRequest {

    private String name;

    private String employee_id;

    private String email_id;

    private String phone_number;

    private String join_date;

    private String role;
    private String department;

}
