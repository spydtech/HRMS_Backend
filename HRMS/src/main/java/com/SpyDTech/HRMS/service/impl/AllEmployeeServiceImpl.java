package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.AddEmployeeRequest;
import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.entities.AllEmployees;
import com.SpyDTech.HRMS.repository.AllEmployeeRepository;
import com.SpyDTech.HRMS.repository.DepartmentRepository;
import com.SpyDTech.HRMS.service.AllEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AllEmployeeServiceImpl implements AllEmployeeService {

    private final AllEmployeeRepository allEmployeeRepository;
    @Autowired
    private  DepartmentRepository departmentRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity addEmployee(AddEmployeeRequest addEmployeeRequest) {
        if(!allEmployeeRepository.existsByName(addEmployeeRequest.getName())){

            List<AllEmployees> savedEmployee = saveEmployee(addEmployeeRequest);
            return ResponseEntity.ok(savedEmployee);
        }
        else{

            ErrorResponse errorResponse = new ErrorResponse("Employee with the same name already exists.");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }


    }

    @Override
    public ResponseEntity updateEmployee(AddEmployeeRequest addEmployeeRequest) {

        AllEmployees allEmployees = setDetails(addEmployeeRequest);
        allEmployeeRepository.save(allEmployees);

        return allEmployeeRepository.findByName(addEmployeeRequest.getName());
    }

    @Override
    public ResponseEntity deleteEmployee(String employeeId) {
        return ResponseEntity.ok(allEmployeeRepository.deleteByEmployeeId(employeeId));
    }


    @Override
    public List<AllEmployees> getEmployee() {
        return allEmployeeRepository.findAll();
    }

    @Override
    public String SendUserNameAndPassword(String email, String password) {
       if(allEmployeeRepository.existsByEmailId(email)){
           return "email already exists";
       }

       AllEmployees allEmployees=new AllEmployees();
       allEmployees.setPassword(passwordEncoder.encode(password));
       allEmployees.setEmailId(email);
       allEmployeeRepository.save(allEmployees);

        emailService.sendEmail(email,"Sending  Password",
                " password is "+password);
        return "email sent successfully";
    }


    public List<AllEmployees> saveEmployee(AddEmployeeRequest addEmployeeRequest) {

        AllEmployees allEmployees = setDetails(addEmployeeRequest);
        allEmployeeRepository.save(allEmployees);

        return getEmployee();
    }

    public AllEmployees setDetails(AddEmployeeRequest addEmployeeRequest){

        AllEmployees setEmployeeDetails = new AllEmployees();
        setEmployeeDetails.setName(addEmployeeRequest.getName());
        setEmployeeDetails.setEmployeeId(addEmployeeRequest.getEmployee_id());
        setEmployeeDetails.setEmailId(addEmployeeRequest.getEmail_id());
        setEmployeeDetails.setPhoneNumber(addEmployeeRequest.getPhone_number());
        setEmployeeDetails.setJoinDate(addEmployeeRequest.getJoin_date());
        setEmployeeDetails.setRole(addEmployeeRequest.getRole());
        return setEmployeeDetails;
    }

}
