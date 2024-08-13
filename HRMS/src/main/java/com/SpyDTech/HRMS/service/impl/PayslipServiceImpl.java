package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.PayslipDto;
import com.SpyDTech.HRMS.dto.PayslipRequest;
import com.SpyDTech.HRMS.entities.AllEmployees;
import com.SpyDTech.HRMS.entities.Payslip;
import com.SpyDTech.HRMS.repository.AllEmployeeRepository;
import com.SpyDTech.HRMS.repository.PayslipRepository;
import com.SpyDTech.HRMS.service.PayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PayslipServiceImpl implements PayslipService {
    @Autowired
    PayslipRepository payslipRepository;
    @Autowired
    AllEmployeeRepository allEmployeeRepository;
    @Override
    public ResponseEntity savePayslip(PayslipRequest payslipRequest) {
        Payslip payslip = new Payslip();
        Optional<Payslip>  payslipMonth=payslipRepository.findByEmployeeAndSalaryMonth(payslipRequest.getEmployee(),payslipRequest.getSalaryMonth());
if(payslipMonth.isPresent()){
    return new ResponseEntity<>("Payslip already Exist with this EmployeeId And Month", HttpStatus.NOT_FOUND);

}
        payslip.setSalaryMonth(payslipRequest.getSalaryMonth());
        Optional<AllEmployees> employeesOptional=allEmployeeRepository.findByEmployeeId(payslipRequest.getEmployee());
        if (employeesOptional.isEmpty()) {
            return new ResponseEntity<>("Employee not found with this EmployeeId", HttpStatus.NOT_FOUND);
        }
        payslip.setEmployee(payslipRequest.getEmployee());
        payslip.setEarnings(payslipRequest.getEarnings());
        payslip.setContributions(payslipRequest.getContributions());
        payslip.setDeductions(payslipRequest.getDeductions());
        BigDecimal totalEarnings = payslipRequest.getEarnings().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalContributions = payslipRequest.getContributions().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDeductions = payslipRequest.getDeductions().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        payslip.setTotalEarnings(totalEarnings);
        payslip.setTotalContributions(totalContributions);
        payslip.setTotalDeductions(totalDeductions);
        BigDecimal netSalary = totalEarnings.subtract(totalContributions).subtract(totalDeductions);
        payslip.setNetSalary(netSalary);
        payslipRepository.save(payslip);
        return ResponseEntity.ok(convertEntityToDto(payslip));
    }

    @Override
    public ResponseEntity getPayslip(String employeeId, YearMonth month) {
       Optional<Payslip>  payslip=payslipRepository.findByEmployeeAndSalaryMonth(employeeId,month);
        if (payslip.isEmpty()) {
            return new ResponseEntity<>("Payslip not found with this EmployeeId And Month", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(convertEntityToDto(payslip.get()));
    }


    public PayslipDto convertEntityToDto(Payslip payslip){
        PayslipDto payslipDto=new PayslipDto();
        payslipDto.setId(payslip.getId());
        payslipDto.setSalaryMonth(formatYearMonth(payslip.getSalaryMonth()));
        payslipDto.setDeductions(payslip.getDeductions());
        payslipDto.setEarnings(payslip.getEarnings());
        payslipDto.setContributions(payslip.getContributions());
        Optional<AllEmployees> employees=allEmployeeRepository.findByEmployeeId(payslip.getEmployee());
        payslipDto.setEmployee(employees.get());
        payslipDto.setTotalDeductions(payslip.getTotalDeductions());
        payslipDto.setTotalEarnings(payslip.getTotalEarnings());
        payslipDto.setTotalContributions(payslip.getTotalContributions());
        payslipDto.setNetSalary(payslip.getNetSalary());
        return payslipDto;
    }
    public static String formatYearMonth(YearMonth yearMonth) {
        if (yearMonth == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        return yearMonth.format(formatter);
    }
}
