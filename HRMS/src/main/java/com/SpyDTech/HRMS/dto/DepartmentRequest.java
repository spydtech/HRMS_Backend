package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.AllEmployees;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DepartmentRequest {
    private Integer id;
    private String departmentName;
    private String departmentHead;
    private List<String> employeesList;
    private Long totalEmployee;
}
