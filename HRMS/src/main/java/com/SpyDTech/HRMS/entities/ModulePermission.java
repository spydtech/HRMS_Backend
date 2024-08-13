package com.SpyDTech.HRMS.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "permissions")
public class ModulePermission {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String moduleName;
    private Boolean readAccess;
    private Boolean writeAccess;
    private Boolean deleteAccess;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
