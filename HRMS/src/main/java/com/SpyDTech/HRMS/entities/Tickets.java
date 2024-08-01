package com.SpyDTech.HRMS.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String assaignBy;
    private String assaignTo;
    private String email;
    private String subject;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    private LocalDate date;


}
