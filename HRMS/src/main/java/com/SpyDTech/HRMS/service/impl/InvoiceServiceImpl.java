package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.dto.InvoiceDto;
import com.SpyDTech.HRMS.dto.InvoiceRequest;
import com.SpyDTech.HRMS.entities.Client;
import com.SpyDTech.HRMS.entities.Invoice;
import com.SpyDTech.HRMS.repository.ClientRepository;
import com.SpyDTech.HRMS.repository.InvoiceRepository;
import com.SpyDTech.HRMS.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public ResponseEntity save(InvoiceRequest invoiceRequest) {
        Invoice invoice=new Invoice();
        invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        Optional<Client> client=clientRepository.findByClientId(invoiceRequest.getClient());
        if(client.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Client Id not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }else{
            invoice.setClient(client.get());
        }
        invoice.setDate(getFormattedDay(invoiceRequest.getDate()));
        invoice.setType(invoiceRequest.getType());
        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setAmount(invoiceRequest.getAmount());
        invoiceRepository.save(invoice);
        return ResponseEntity.ok(entityToDto(invoice));
    }



    @Override
    public ResponseEntity getInvoices() {
        List<Invoice> invoiceList=invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtos=new ArrayList<>();
        for(Invoice invoice:invoiceList){
            invoiceDtos.add(entityToDto(invoice));
        }
        return ResponseEntity.ok(invoiceDtos);
    }

    @Override
    public ResponseEntity updateInvoice(Integer id, InvoiceRequest invoiceRequest) {
        Optional<Invoice> optionalInvoice=invoiceRepository.findById(id);
        if(optionalInvoice.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("Invoice Id not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        Invoice invoice=optionalInvoice.get();
        if(invoiceRequest.getInvoiceNumber()!=null) {
            invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        }
        if(invoiceRequest.getClient()!=null) {
            Optional<Client> client = clientRepository.findByClientId(invoiceRequest.getClient());
            if (client.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("Client Id not found");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            } else {
                invoice.setClient(client.get());
            }
        }
        if(invoiceRequest.getDate()!=null) {
            invoice.setDate(getFormattedDay(invoiceRequest.getDate()));
        }
        if(invoiceRequest.getType()!=null) {
            invoice.setType(invoiceRequest.getType());
        }
        if(invoiceRequest.getStatus()!=null) {
            invoice.setStatus(invoiceRequest.getStatus());
        }
        if(invoiceRequest.getAmount()!=null) {
            invoice.setAmount(invoiceRequest.getAmount());
        }
        invoiceRepository.save(invoice);
        return ResponseEntity.ok(entityToDto(invoice));
    }

    @Override
    public boolean deleteInvoice(Integer invoiceId) {
        if (invoiceId != null && invoiceRepository.existsById(invoiceId)) {
            invoiceRepository.deleteById(invoiceId);
            return true;
        }
        return false;
    }

    public InvoiceDto entityToDto(Invoice invoice){
      InvoiceDto invoiceDto=new InvoiceDto();
      invoiceDto.setId(invoice.getId());
      invoiceDto.setInvoiceNumber(invoice.getInvoiceNumber());
      Optional<Client> client=clientRepository.findById(invoice.getClient().getId());
      invoiceDto.setClient(client.get().getClientId());
      invoiceDto.setDate(invoice.getDate());
      invoiceDto.setType(invoice.getType());
      invoiceDto.setStatus(invoice.getStatus());
//      String dollars=convertToDollars(invoice.getAmount());
      invoiceDto.setAmount(invoice.getAmount());
      return invoiceDto;
  }

//  public static String convertToDollars(BigDecimal bigDecimal){
//      NumberFormat numberFormat=NumberFormat.getCurrencyInstance(Locale.US);
//      return numberFormat.format(bigDecimal);
//  }

    private String getFormattedDay(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        return date.format(formatter);
    }
}
