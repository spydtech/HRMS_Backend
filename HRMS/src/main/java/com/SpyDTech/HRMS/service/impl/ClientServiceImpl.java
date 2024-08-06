package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.ClientRequest;
import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.entities.Client;
import com.SpyDTech.HRMS.repository.ClientRepository;
import com.SpyDTech.HRMS.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public ResponseEntity saveClient(ClientRequest clientRequest) {
        Client client=new Client();
        client.setFirstName(clientRequest.getFirstName());
        if(clientRequest.getLastName()!=null) {
            client.setLastName(clientRequest.getLastName());
        }
        client.setEmailId(clientRequest.getEmailId());
        client.setPassword(clientRequest.getPassword());
        if(clientRequest.getConformPassword().equals(clientRequest.getPassword())) {
            client.setConformPassword(clientRequest.getConformPassword());
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Client Password and Conform Password must be same.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        if(clientRequest.getMobileNo()!=null) {
            client.setMobileNo(clientRequest.getMobileNo());
        }
        client.setUserName(clientRequest.getUserName());
        if(clientRequest.getAddress()!=null) {
            client.setAddress(clientRequest.getAddress());
        }
        Optional<Client> optionalClient=clientRepository.findByClientId(clientRequest.getClientId());
        if(optionalClient.isEmpty()) {
            client.setClientId(clientRequest.getClientId());
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Client with the same ClientId already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        clientRepository.save(client);
        return ResponseEntity.ok(client);
    }
}
