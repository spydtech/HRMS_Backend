package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LeaveAcceptOrDeclineDTO {

    private String email;

    private String employeeId;

    private LocalDate fromDate;

    private LocalDate endDate;

    private LeaveStatus leaveStatus;

    private String rejectReason;
}
