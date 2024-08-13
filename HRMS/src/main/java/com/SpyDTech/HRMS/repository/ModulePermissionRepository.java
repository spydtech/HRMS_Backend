package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.ModulePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulePermissionRepository extends JpaRepository<ModulePermission,Long> {
}
