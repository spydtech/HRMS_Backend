package com.SpyDTech.HRMS.entities;


import com.SpyDTech.HRMS.service.impl.LocalDateAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name ="leave_request")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String employeeId;

    private String leaveType;

    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fromDate;

    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate endDate;

    private String reason;

    private String rejectReason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus = LeaveStatus.PENDING;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime requestCreationDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime acceptOrRejectDate;



    @PrePersist
    protected void onCreate() {
        this.requestCreationDate = LocalDateTime.now();
    }


}
