package com.SpyDTech.HRMS.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamClass {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String teamName;
    @Lob
    private byte [] image;
    private String teamLeadName;
    private List<String> courses;
    private List<String> employees;
    private int teamLeadCount;
    private int employeesCount;

}
