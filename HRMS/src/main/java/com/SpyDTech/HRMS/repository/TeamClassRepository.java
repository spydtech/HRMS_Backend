package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.TeamClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamClassRepository extends JpaRepository<TeamClass,Long> {
    Optional<TeamClass> findByTeamName(String name);
}
