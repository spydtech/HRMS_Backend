package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.ClientListRequest;
import com.SpyDTech.HRMS.dto.ClientRequest;
import com.SpyDTech.HRMS.dto.DepartmentRequest;
import com.SpyDTech.HRMS.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    ClientService clientService;


    @PostMapping("/create/client")
    public ResponseEntity saveClient(@RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.saveClient(clientRequest));
    }

    @PostMapping("/create/clientList")
    public ResponseEntity saveClientList(@RequestBody ClientListRequest clientListRequest) {
        return ResponseEntity.ok(clientService.saveClientList(clientListRequest));
    }


    @GetMapping("get/clientList")
    public ResponseEntity getclientList(){
        return ResponseEntity.ok(clientService.getclientList());
    }

    @GetMapping("get/clientDetails")
    public ResponseEntity getClientDetails(@RequestParam String companyName,@RequestParam String role){
        return ResponseEntity.ok(clientService.getClientDetails(companyName,role));
    }
}
