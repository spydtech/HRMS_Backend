package com.SpyDTech.HRMS.dto;

import com.SpyDTech.HRMS.entities.ClientList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientDetailsDto {
    private ClientListRequest clientListRequest;
    private List<TeamDetailsDto> teamDetailsDtoList;

}
