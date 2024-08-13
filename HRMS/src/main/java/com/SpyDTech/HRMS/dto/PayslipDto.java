package com.SpyDTech.HRMS.dto;


import com.SpyDTech.HRMS.entities.AllEmployees;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PayslipDto {
    private long id;
    private String salaryMonth;
    private AllEmployees employee;
    private Map<String, BigDecimal> earnings;
    private Map<String, BigDecimal> contributions;
    private Map<String, BigDecimal> deductions;
    private BigDecimal totalEarnings;
    private BigDecimal totalContributions;
    private BigDecimal totalDeductions;
    private BigDecimal netSalary;
}
