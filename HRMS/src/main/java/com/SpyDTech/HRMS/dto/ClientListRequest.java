package com.SpyDTech.HRMS.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class  ClientListRequest {
    private long id;
    private String clientId;
    private String clientName;
    private String role;
    private String companyName;
}
