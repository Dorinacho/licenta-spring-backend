package com.thesis.backend.controllers;

import com.thesis.backend.entities.Membership;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.MembershipRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082"})
@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    @Autowired
    MembershipRepository membershipRepository;

    @GetMapping
//    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public List<Membership> getMemberships() {
        return membershipRepository.findAll();
    }

    @PostMapping("/addMembership")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addMembership(@RequestBody @NotNull Membership membership){
        membershipRepository.save(membership);
        return ResponseEntity.ok(new MessageResponse("Membership added successfully!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateMembership(@PathVariable(value = "id") int id ,@RequestBody @NotNull Membership membershipData){
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id " + id));
        membership.setName(membershipData.getName());
        membership.setDescription(membershipData.getDescription());
        membership.setPrice(membershipData.getPrice());
        membership.setDiscount(membershipData.getDiscount());
        membershipRepository.save(membership);
        return ResponseEntity.ok(new MessageResponse("Membership updated successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMembership(@PathVariable(value = "id") int id) {
        membershipRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Membership deleted successfully!"));
    }
}
