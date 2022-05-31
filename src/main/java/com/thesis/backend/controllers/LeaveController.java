package com.thesis.backend.controllers;

import com.thesis.backend.entities.Leave;
import com.thesis.backend.entities.LeaveStatus;
import com.thesis.backend.entities.LeaveType;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.LeaveRepository;
import com.thesis.backend.repository.LeaveStatusRepository;
import com.thesis.backend.repository.LeaveTypeRepository;
import com.thesis.backend.services.LeaveService;
import org.jetbrains.annotations.NotNull;
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
    LeaveStatusRepository leaveStatusRepository;

    @Autowired
    LeaveService leaveService;

    @GetMapping("/getAllRequests")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Leave> getAllLeaveRequests() {
        return leaveRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public List<Leave> getRequests(@PathVariable(value = "id") int id) {
        return leaveService.getRequests(id);
    }

    @GetMapping("/leaveStatus")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<LeaveStatus> getLeaveStatus() {
        return leaveStatusRepository.findAll();
    }

    @PostMapping("/sendLeaveRequest")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> addLeaveRequest(@RequestBody Leave leave) {
        leaveService.sendLeaveRequest(leave);
        return ResponseEntity.ok(new MessageResponse("Leave request sent successfully!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<?> updateLeaveRequest(@PathVariable(value = "id") int leaveId, @RequestBody @NotNull Leave leave) {
        leaveService.updateLeave(leaveId, leave);
        return ResponseEntity.ok(new MessageResponse("Employee edited successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<?> deleteLeaveRequest(@PathVariable(value = "id") int id) {
        leaveRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Leave request deleted successfully!"));
    }

    @GetMapping("/approved/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public List<Leave> getApprovedRequests(@PathVariable(value = "id") int userId){
        return leaveService.getApprovedReq(userId);
    }
}
