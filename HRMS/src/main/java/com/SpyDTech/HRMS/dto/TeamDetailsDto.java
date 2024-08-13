package com.SpyDTech.HRMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamDetailsDto {
    private String teamName;
    private Long projectCount;
    private Integer employeeCount;
    private BigDecimal totalBudget;
}
