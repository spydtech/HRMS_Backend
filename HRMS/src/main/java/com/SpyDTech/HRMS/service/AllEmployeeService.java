package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.AddEmployeeRequest;
import com.SpyDTech.HRMS.entities.AllEmployees;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AllEmployeeService {

    ResponseEntity addEmployee(AddEmployeeRequest addEmployeeRequest);

    ResponseEntity updateEmployee(AddEmployeeRequest addEmployeeRequest);

    ResponseEntity deleteEmployee(String employee_id);

    List<AllEmployees> getEmployee();

    String SendUserNameAndPassword(String email,String password);
}
