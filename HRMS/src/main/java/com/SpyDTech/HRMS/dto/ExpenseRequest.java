package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.ExpenseStatus;
import com.SpyDTech.HRMS.entities.PaidType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExpenseRequest {
    private Integer id;
    private String item;
    private String orderBy;
    private String from;
    private LocalDate date;
    private PaidType paidBy;
    private ExpenseStatus status;
    private BigDecimal amount;
}
