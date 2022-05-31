package com.thesis.backend.controllers;

import com.thesis.backend.entities.LeaveType;
import com.thesis.backend.entities.enums.ELeaveType;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.LeaveTypeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/leaveType")
public class LeaveTypeController {

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<LeaveType> getLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addLeaveType(@RequestBody @NotNull LeaveType leaveType){
        leaveTypeRepository.save(leaveType);
        return ResponseEntity.ok(new MessageResponse("Leave type added successfully!"));
    }
}
