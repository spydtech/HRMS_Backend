package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.DepartmentRequest;
import com.SpyDTech.HRMS.repository.DepartmentRepository;
import com.SpyDTech.HRMS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    @PostMapping("/create/department")
    public ResponseEntity saveDepartment(@RequestBody DepartmentRequest departmentRequest){
        return ResponseEntity.ok(departmentService.saveDepartment(departmentRequest));
    }

    @PutMapping("/edit/department/{depName}")
    public ResponseEntity updateDepartment(@PathVariable String depName, @RequestBody DepartmentRequest departmentRequest){
        return ResponseEntity.ok(departmentService.updateDepartment(depName,departmentRequest));
    }

    @DeleteMapping("/delete/departmentName/{depName}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String depName){
        boolean isRemoved = departmentService.deleteDepartment(depName);
        if (isRemoved) {
            return new ResponseEntity<>("Department deleted successfully",  HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
        }
    }

@GetMapping("get/department")
    public ResponseEntity getDepartments(){
        return ResponseEntity.ok(departmentService.getDepartments());
}
}
