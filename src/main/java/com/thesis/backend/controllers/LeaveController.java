package com.thesis.backend.controllers;

import com.thesis.backend.entities.Employee;
import com.thesis.backend.entities.Leave;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.LeaveRepository;
import com.thesis.backend.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    LeaveRepository leaveRepository;

@Autowired
    EmployeesRepository employeesRepository;

@Autowired
    LeaveService leaveService;

    @GetMapping("/getAllRequests")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Leave> getAllLeaveRequests() {
        return leaveRepository.findAll();
    }

    @GetMapping("/getRequests")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public List<Leave> getRequests() {
        return leaveRepository.findAll();
    }

    @PostMapping("/sendLeaveRequest")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> addLeaveRequest(@RequestBody Leave leave) {
        leaveService.sendLeaveRequest(leave);
        return ResponseEntity.ok(new MessageResponse("Leave request send successfully!"));
    }
}
