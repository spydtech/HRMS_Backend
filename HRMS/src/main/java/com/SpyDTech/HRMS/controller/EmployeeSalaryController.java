package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.EmployeeSalaryDto;
import com.SpyDTech.HRMS.dto.ExpenseRequest;
import com.SpyDTech.HRMS.service.EmployeeSalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeSalaryController {
    @Autowired
    EmployeeSalaryService employeeSalaryService;

    @PostMapping("/create/salary")
    public ResponseEntity saveSalary(@RequestBody EmployeeSalaryDto employeeSalaryDto){
        return ResponseEntity.ok(employeeSalaryService.saveSalary(employeeSalaryDto));
    }

    @PutMapping("/edit/salary/{employeeId}")
    public ResponseEntity updateEmployeeSalary(@PathVariable String employeeId,@RequestBody EmployeeSalaryDto employeeSalaryDto){
        return ResponseEntity.ok(employeeSalaryService.updateEmployeeSalary(employeeId,employeeSalaryDto));
    }
    @DeleteMapping("/delete/salary/{employeeId}")
    public ResponseEntity<String> deleteSalary(@PathVariable String employeeId){
        boolean isRemoved = employeeSalaryService.deleteSalary(employeeId);
        if (isRemoved) {
            return new ResponseEntity<>("Employee Salary deleted successfully",  HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee salary not found for the given Employee ID", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("get/salary")
    public ResponseEntity getSalary(){
        return ResponseEntity.ok(employeeSalaryService.getSalary());
    }
}
