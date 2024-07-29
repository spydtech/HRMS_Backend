package com.SpyDTech.HRMS.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HolidayRequest {
    private Long id;
    private LocalDate day;
    private String date;
    private String holidayName;
}
