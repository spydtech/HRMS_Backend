package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.AddProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<AddProject,Long> {
    List<AddProject> findAllByClientName(String companyName);
}
