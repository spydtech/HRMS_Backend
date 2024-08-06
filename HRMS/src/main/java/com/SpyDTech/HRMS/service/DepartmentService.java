package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.DepartmentRequest;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    ResponseEntity saveDepartment(DepartmentRequest departmentRequest);

    ResponseEntity updateDepartment(String depName, DepartmentRequest departmentRequest);

    boolean deleteDepartment(String depName);

    ResponseEntity getDepartments();
}
