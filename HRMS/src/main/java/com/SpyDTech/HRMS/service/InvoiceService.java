package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.InvoiceRequest;
import org.springframework.http.ResponseEntity;

public interface InvoiceService {
    ResponseEntity save(InvoiceRequest invoiceRequest);

    ResponseEntity getInvoices();

    ResponseEntity updateInvoice(Integer id, InvoiceRequest invoiceRequest);

    boolean deleteInvoice(Integer invoiceId);
}
