package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.PayslipRequest;
import org.springframework.http.ResponseEntity;

import java.time.YearMonth;

public interface PayslipService {
    ResponseEntity savePayslip(PayslipRequest payslipRequest);

    ResponseEntity getPayslip(String employeeId, YearMonth month);
}
