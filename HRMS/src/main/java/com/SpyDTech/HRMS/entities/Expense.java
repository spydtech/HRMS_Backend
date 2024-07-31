package com.SpyDTech.HRMS.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String item;
    @ManyToOne
    @JoinColumn(name = "order_by")
    private AllEmployees orderBy;
    @Column(name = "source")
    private String from;
    private String date;
    @Enumerated(EnumType.STRING)
    private PaidType paidBy;
    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;
    private BigDecimal amount;
}
