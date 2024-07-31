package com.SpyDTech.HRMS.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name ="all_employee")
public class AllEmployees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String employeeId;

    private String emailId;

    private String phoneNumber;

    private String joinDate;

    private String role;
    @JsonIgnore
    @OneToMany(mappedBy = "orderBy")
    private List<Expense> expenses;
}
