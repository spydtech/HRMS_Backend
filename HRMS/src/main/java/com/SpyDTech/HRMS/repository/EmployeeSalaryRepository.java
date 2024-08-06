package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary,Integer> {
    boolean existsByEmployeeId(String employeeId);

    void deleteByEmployeeId(String employeeId);

    Optional<EmployeeSalary> findByEmployeeId(String employeeId);
}
