package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.LeaveRequest;
import com.SpyDTech.HRMS.entities.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {


    List<LeaveRequest> findAllByLeaveStatus(LeaveStatus leaveStatus);

    List<LeaveRequest> findByEmployeeId(String employeeId);
}
