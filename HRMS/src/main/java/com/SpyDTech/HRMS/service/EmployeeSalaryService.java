package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.EmployeeSalaryDto;
import org.springframework.http.ResponseEntity;

public interface EmployeeSalaryService {
    ResponseEntity saveSalary(EmployeeSalaryDto employeeSalaryDto);

    ResponseEntity getSalary();

    boolean deleteSalary(String employeeId);

    ResponseEntity updateEmployeeSalary(String employeeId, EmployeeSalaryDto employeeSalaryDto);
}
