package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip,Long> {
    Optional<Payslip> findByEmployeeAndSalaryMonth(String employeeId, YearMonth month);
}
