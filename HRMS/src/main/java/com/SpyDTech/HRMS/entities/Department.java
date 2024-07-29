package com.SpyDTech.HRMS.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name ="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String departmentName;
    private String departmentHead;
    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<AllEmployees> employeesList;
    private Long totalEmployee;
}
