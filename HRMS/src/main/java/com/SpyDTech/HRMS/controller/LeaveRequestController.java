package com.SpyDTech.HRMS.controller;


import com.SpyDTech.HRMS.dto.LeaveAcceptOrDeclineDTO;
import com.SpyDTech.HRMS.dto.LeaveRequestDTO;
import com.SpyDTech.HRMS.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/addLeaveRequest")
    public ResponseEntity addLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
        return leaveRequestService.createALeaveRequest(leaveRequestDTO);
    }

    @PostMapping("/leaveRequestAction")
    public ResponseEntity leaveRequestAction(@RequestBody LeaveAcceptOrDeclineDTO leaveAcceptOrDeclineDTO){
        return leaveRequestService.acceptOrDeclineOrCancel(leaveAcceptOrDeclineDTO);
    }

    @GetMapping("/allPendingLeaveRequests")
    public ResponseEntity allLeaveRequests(){
        return leaveRequestService.getAllPendingRequest();
    }

    @GetMapping("/CurrentUserLeaveRequests")
    public ResponseEntity currentUserLeaveRequest(){
        return leaveRequestService.getAllCurrentUserLeaveRequest();
    }

}
