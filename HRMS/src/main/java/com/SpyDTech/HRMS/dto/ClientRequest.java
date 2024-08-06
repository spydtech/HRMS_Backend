package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.Invoice;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String userName;
    private String password;
    private String conformPassword;
    private String mobileNo;
    private String clientId;
    private String address;
}
