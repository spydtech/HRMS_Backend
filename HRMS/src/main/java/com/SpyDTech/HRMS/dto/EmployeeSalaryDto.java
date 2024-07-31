package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.AllEmployees;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeSalaryDto {
    private Integer id;
    private String name;
    private String email;
    private String employeeId;
    private String phone;
    private String joinDate;
    private String role;
    private float salary;
}
