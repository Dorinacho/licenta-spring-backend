package com.thesis.backend.controllers;

import com.thesis.backend.entities.Membership;
import com.thesis.backend.entities.leave.LeaveType;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.MembershipRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/membership")
public class MembershipController {

    @Autowired
    MembershipRepository membershipRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public List<Membership> getMemberships() {
        return membershipRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addMembership(@RequestBody @NotNull Membership membership){
        membershipRepository.save(membership);
        return ResponseEntity.ok(new MessageResponse("Membership added successfully!"));
    }
}
