package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.DailyAttendanceDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AttendanceService {

     ResponseEntity punchIn(String email);

     ResponseEntity punchOut(String email);

     List<DailyAttendanceDTO> getMonthlyAttendance(int month, int year);
}
