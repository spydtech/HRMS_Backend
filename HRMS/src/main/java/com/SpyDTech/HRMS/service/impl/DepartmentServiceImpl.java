package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.DepartmentRequest;
import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.entities.AllEmployees;
import com.SpyDTech.HRMS.entities.Department;
import com.SpyDTech.HRMS.repository.AllEmployeeRepository;
import com.SpyDTech.HRMS.repository.DepartmentRepository;
import com.SpyDTech.HRMS.service.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AllEmployeeRepository allEmployeeRepository;

    @Override
    public ResponseEntity saveDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        if(Boolean.FALSE.equals(isValid(departmentRequest.getDepartmentName()))){
            ErrorResponse errorResponse = new ErrorResponse("Department name can't be null/empty..");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        if(Boolean.FALSE.equals(isValid(departmentRequest.getDepartmentHead()))){
            ErrorResponse errorResponse = new ErrorResponse("Department Head can't be null/empty..");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        if(Boolean.FALSE.equals(isValidLong(departmentRequest.getTotalEmployee()))){
            ErrorResponse errorResponse = new ErrorResponse("Total Employees can't be null/empty..");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }


        if (!departmentRepository.existsByDepartmentName(departmentRequest.getDepartmentName())) {
            department.setDepartmentName(departmentRequest.getDepartmentName());
            Optional<AllEmployees> departmentHead = allEmployeeRepository.findByEmployeeId(departmentRequest.getDepartmentHead());
            if (departmentHead.isEmpty()) {
                return new ResponseEntity<>("Employee not found with this EmployeeId", HttpStatus.NOT_FOUND);
            }
            department.setDepartmentHead(departmentHead.get().getName());
            department.setTotalEmployee(departmentRequest.getTotalEmployee());
            departmentRepository.save(department);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Department with the same name already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        return ResponseEntity.ok(entityToDto(department));

    }

    public Boolean isValid(String value){
        if((value!=null) && (!value.trim().isEmpty())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public Boolean isValidLong(Long value){
        if((value!=null)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public ResponseEntity updateDepartment(String depName, DepartmentRequest departmentRequest) {
       Optional<Department> optionalDepartment=departmentRepository.findByDepartmentName(depName);
        Department department=new Department();
       if(optionalDepartment.isPresent()){
            department=optionalDepartment.get();
            if(departmentRequest.getDepartmentHead()!=null) {
                department.setDepartmentHead(departmentRequest.getDepartmentHead());
            }
           if (departmentRequest.getDepartmentName() != null) {
               Optional<Department> optionalDepartment1=departmentRepository.findByDepartmentName(departmentRequest.getDepartmentName());
               if(optionalDepartment1.isEmpty() || optionalDepartment1.get().getId().equals(optionalDepartment.get().getId())){
                   department.setDepartmentName(departmentRequest.getDepartmentName());
               }else{
                   ErrorResponse errorResponse = new ErrorResponse("Department with the same name already exists.");
                   return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
               }
               if (departmentRequest.getTotalEmployee() != null) {
                   department.setTotalEmployee(departmentRequest.getTotalEmployee());
               }
           }
            departmentRepository.save(department);
       }
       return ResponseEntity.ok(department);
    }

    @Override
    @Transactional
    public boolean deleteDepartment(String depName) {
        if (depName != null && departmentRepository.existsByDepartmentName(depName)) {
            departmentRepository.deleteByDepartmentName(depName);
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity getDepartments() {
       List<Department> departments=departmentRepository.findAll();
       return ResponseEntity.ok(departments);
    }

    private DepartmentRequest entityToDto(Department department) {
        DepartmentRequest departmentRequest=new DepartmentRequest();
        departmentRequest.setId(department.getId());
        departmentRequest.setDepartmentHead(department.getDepartmentHead());
        departmentRequest.setDepartmentName(department.getDepartmentName());
        departmentRequest.setTotalEmployee(department.getTotalEmployee());
        return departmentRequest;
    }


}
