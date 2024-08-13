package com.SpyDTech.HRMS.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ModulePermissionDto {
    private long id;
    private String moduleName;
    private Boolean readAccess;
    private Boolean writeAccess;
    private Boolean deleteAccess;
}
