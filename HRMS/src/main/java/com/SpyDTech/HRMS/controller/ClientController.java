package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.ClientRequest;
import com.SpyDTech.HRMS.dto.DepartmentRequest;
import com.SpyDTech.HRMS.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    ClientService clientService;


    @PostMapping("/create/client")
    public ResponseEntity saveClient(@RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.saveClient(clientRequest));
    }
}
