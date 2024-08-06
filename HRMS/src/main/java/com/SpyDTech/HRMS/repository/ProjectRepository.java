package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.AddProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<AddProject,Long> {
}
