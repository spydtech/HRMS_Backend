package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.AllEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllEmployeeRepository extends JpaRepository<AllEmployees,Long> {
    boolean existsByName(String name);

    ResponseEntity findByName(String name);


    Object deleteByEmployeeId(String employeeId);

    boolean existsByEmailId(String email);

    boolean existsByEmployeeId(String employeeid);

    Optional<AllEmployees> findByEmployeeId(String departmentHead);


    List<AllEmployees> findAllByDepartmentDepartmentName(String departmentName);

    List<AllEmployees> findAllByEmployeeIdIn(List<String> employeesList);
}
