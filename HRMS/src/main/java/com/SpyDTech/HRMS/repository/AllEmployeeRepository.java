package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.AllEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AllEmployeeRepository extends JpaRepository<AllEmployees,Long> {
    boolean existsByName(String name);

    ResponseEntity findByName(String name);


    Object deleteByEmployeeId(String employeeId);

    boolean existsByEmailId(String email);

    boolean existsByEmployeeId(String employeeid);
}
