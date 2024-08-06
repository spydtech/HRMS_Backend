package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.ExpenseRequest;
import com.SpyDTech.HRMS.dto.InvoiceRequest;
import com.SpyDTech.HRMS.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/create/invoice")
    public ResponseEntity saveInvoice(@RequestBody InvoiceRequest invoiceRequest){
        return ResponseEntity.ok(invoiceService.save(invoiceRequest));
    }

    @PutMapping("/edit/invoice/{id}")
    public ResponseEntity updateInvoice(@PathVariable Integer id,@RequestBody InvoiceRequest invoiceRequest){
        return ResponseEntity.ok(invoiceService.updateInvoice(id,invoiceRequest));
    }
    @DeleteMapping("/delete/invoice/{invoiceId}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Integer invoiceId){
        boolean isRemoved = invoiceService.deleteInvoice(invoiceId);
        if (isRemoved) {
            return new ResponseEntity<>("invoice deleted successfully",  HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invoice not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get/invoices")
    public ResponseEntity getInvoices(){
        return ResponseEntity.ok(invoiceService.getInvoices());
    }
}
