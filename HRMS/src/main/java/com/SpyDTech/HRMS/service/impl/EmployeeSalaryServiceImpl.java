package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.EmployeeSalaryDto;
import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.entities.AllEmployees;
import com.SpyDTech.HRMS.entities.EmployeeSalary;
import com.SpyDTech.HRMS.repository.AllEmployeeRepository;
import com.SpyDTech.HRMS.repository.EmployeeSalaryRepository;
import com.SpyDTech.HRMS.service.EmployeeSalaryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSalaryServiceImpl implements EmployeeSalaryService {
    @Autowired
    AllEmployeeRepository allEmployeeRepository;
    @Autowired
    EmployeeSalaryRepository employeeSalaryRepository;
    @Override
    public ResponseEntity saveSalary(EmployeeSalaryDto employeeSalaryDto) {
        if( employeeSalaryDto.getSalary() == 0.0f) {
            ErrorResponse errorResponse = new ErrorResponse("Salary can't be null/empty..");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        if(Boolean.FALSE.equals(isValid(employeeSalaryDto.getEmployeeId()))){
            ErrorResponse errorResponse = new ErrorResponse("EmployeeId can't be null/empty..");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        EmployeeSalary employeeSalary=new EmployeeSalary();
        Optional<AllEmployees> optional=allEmployeeRepository.findByEmployeeId(employeeSalaryDto.getEmployeeId());
        if(optional.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("Employee Id  not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        employeeSalary.setEmployeeId(employeeSalaryDto.getEmployeeId());
       employeeSalary.setSalary(employeeSalaryDto.getSalary());
       employeeSalaryRepository.save(employeeSalary);
       return ResponseEntity.ok(employeeSalary);
    }



    @Override
    public ResponseEntity getSalary() {
        List<EmployeeSalary> employeeSalaryList=employeeSalaryRepository.findAll();
        List<EmployeeSalaryDto> salaryDtos=new ArrayList<>();
        for(EmployeeSalary employeeSalary:employeeSalaryList){
            salaryDtos.add(convertEntityToDto(employeeSalary));
        }
        return ResponseEntity.ok(salaryDtos);
    }

    public Boolean isValid(String value){
        if((value!=null) && (!value.trim().isEmpty())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    @Transactional
    public boolean deleteSalary(String employeeId) {
        if (employeeId != null && employeeSalaryRepository.existsByEmployeeId(employeeId)) {
            employeeSalaryRepository.deleteByEmployeeId(employeeId);
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity updateEmployeeSalary(String employeeId, EmployeeSalaryDto employeeSalaryDto) {
        Optional<EmployeeSalary> optional=employeeSalaryRepository.findByEmployeeId(employeeId);
        if(optional.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("Employee salary not found for the given Employee ID");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        EmployeeSalary employeeSalary=optional.get();
        if( employeeSalaryDto.getSalary() != 0.0f) {
            employeeSalary.setSalary(employeeSalaryDto.getSalary());
        }
        employeeSalaryRepository.save(employeeSalary);
        return ResponseEntity.ok(employeeSalary);
    }

    public EmployeeSalaryDto convertEntityToDto(EmployeeSalary employeeSalary){
        EmployeeSalaryDto employeeSalaryDto=new EmployeeSalaryDto();
        employeeSalaryDto.setId(employeeSalary.getId());
        employeeSalaryDto.setSalary(employeeSalary.getSalary());
        Optional<AllEmployees> optional=allEmployeeRepository.findByEmployeeId(employeeSalary.getEmployeeId());
        employeeSalaryDto.setEmail(optional.get().getEmailId());
        employeeSalaryDto.setName(optional.get().getName());
        employeeSalaryDto.setRole(optional.get().getRole());
        employeeSalaryDto.setPhone(optional.get().getPhoneNumber());
        employeeSalaryDto.setJoinDate(optional.get().getJoinDate());
        employeeSalaryDto.setEmployeeId(optional.get().getEmployeeId());
        return employeeSalaryDto;
    }
}
