package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.AttendanceReport;
import com.SpyDTech.HRMS.dto.DailyAttendanceDTO;
import com.SpyDTech.HRMS.dto.EmployeeAttendanceDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AttendanceService {

     ResponseEntity punchIn(/*String email*/);

     ResponseEntity punchOut(/*String email*/);

     List<DailyAttendanceDTO> getMonthlyAttendance(int month, int year);

     List<AttendanceReport> getAllEmployeeAttendanceReport(int year, int month);

     EmployeeAttendanceDTO getEmployeeAttendanceDetail(String employeeId, int year, int month);
}
