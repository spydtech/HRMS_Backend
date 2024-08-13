package com.SpyDTech.HRMS.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PayslipRequest {
    private long id;
    private YearMonth salaryMonth;
    private String employee;
    private Map<String, BigDecimal> earnings;
    private Map<String, BigDecimal> contributions;
    private Map<String, BigDecimal> deductions;

}
