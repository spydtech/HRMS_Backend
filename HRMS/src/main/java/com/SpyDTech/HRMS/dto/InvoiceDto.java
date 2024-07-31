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
public class InvoiceDto {
    private Integer id;
    private String invoiceNumber;
    private String client;
    private String date;
    private PaidType type;
    private ExpenseStatus status;
    private BigDecimal amount;
}
