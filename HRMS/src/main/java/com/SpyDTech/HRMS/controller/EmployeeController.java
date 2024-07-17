package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.AddEmployeeRequest;
import com.SpyDTech.HRMS.service.AllEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class EmployeeController {


    @Autowired
    private  AllEmployeeService allEmployeeService;

    @PostMapping
    public ResponseEntity saveEmployees(@RequestBody AddEmployeeRequest addEmployeeRequest){
        return ResponseEntity.ok(allEmployeeService.addEmployee(addEmployeeRequest));
    }

    @PutMapping
    public ResponseEntity updateEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest){
        return null;
    }

    @GetMapping
    public ResponseEntity getAllEmployee(){
        return ResponseEntity.ok(allEmployeeService.getEmployee());
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable String employeeId){

        return allEmployeeService.deleteEmployee(employeeId);
    }
}
