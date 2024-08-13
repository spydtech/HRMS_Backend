package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.InvoiceRequest;
import com.SpyDTech.HRMS.dto.PayslipRequest;
import com.SpyDTech.HRMS.service.PayslipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PayslipController {
   @Autowired
    PayslipService payslipService;

    @PostMapping("/create/payslip")
    public ResponseEntity savePayslip(@RequestBody PayslipRequest payslipRequest){
        return ResponseEntity.ok(payslipService.savePayslip(payslipRequest));
    }

    @GetMapping("get/payslips")
    public ResponseEntity getPayslips(@RequestParam String employeeId, @RequestParam YearMonth month){
        return ResponseEntity.ok(payslipService.getPayslip(employeeId,month));
    }
}
