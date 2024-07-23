package com.SpyDTech.HRMS.service;


import com.SpyDTech.HRMS.dto.LeaveAcceptOrDeclineDTO;
import com.SpyDTech.HRMS.dto.LeaveRequestDTO;
import org.springframework.http.ResponseEntity;

public interface LeaveRequestService {

    ResponseEntity createALeaveRequest(LeaveRequestDTO leaveRequestDTO);

    ResponseEntity acceptOrDeclineOrCancel(LeaveAcceptOrDeclineDTO leaveAcceptOrDeclineDTO);

    ResponseEntity getAllPendingRequest();

    ResponseEntity getAllCurrentUserLeaveRequest();
}
