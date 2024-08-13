package com.SpyDTech.HRMS.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String invoiceNumber;
    private String client;
    private String date;
    @Enumerated(EnumType.STRING)
    private PaidType type;
    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;
    private BigDecimal amount;
}
