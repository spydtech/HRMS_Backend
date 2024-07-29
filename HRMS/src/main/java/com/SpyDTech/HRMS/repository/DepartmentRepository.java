package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsByDepartmentName(String departmentName);

    Optional<Department> findByDepartmentName(String department);

    void deleteByDepartmentName(String depName);
}
