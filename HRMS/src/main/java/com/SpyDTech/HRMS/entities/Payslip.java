package com.SpyDTech.HRMS.entities;


import com.SpyDTech.HRMS.service.YearMonthConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name ="payslip")
public class Payslip {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Convert(converter = YearMonthConverter.class)
    private YearMonth salaryMonth;
    private String employee;
    @ElementCollection
    @CollectionTable(name = "earnings", joinColumns = @JoinColumn(name = "payslip_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "earning_type")
    @Column(name = "amount")
    private Map<String, BigDecimal> earnings;
    @ElementCollection
    @CollectionTable(name = "contributions", joinColumns = @JoinColumn(name = "payslip_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "contribution_type")
    @Column(name = "amount")
    private Map<String, BigDecimal> contributions;
    @ElementCollection
    @CollectionTable(name = "deductions", joinColumns = @JoinColumn(name = "payslip_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "deduction_type")
    @Column(name = "amount")
    private Map<String, BigDecimal> deductions;
    private BigDecimal totalEarnings;
    private BigDecimal totalContributions;
    private BigDecimal totalDeductions;
    private BigDecimal netSalary;

}
