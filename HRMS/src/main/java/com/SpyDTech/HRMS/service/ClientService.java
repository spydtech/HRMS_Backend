package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.ClientListRequest;
import com.SpyDTech.HRMS.dto.ClientRequest;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ResponseEntity saveClient(ClientRequest clientRequest);

    ResponseEntity saveClientList(ClientListRequest clientListRequest);

    ResponseEntity getclientList();

    ResponseEntity getClientDetails(String companyName, String role);
}
