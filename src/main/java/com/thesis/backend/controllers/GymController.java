package com.thesis.backend.controllers;

import com.thesis.backend.entities.Gym;
import com.thesis.backend.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/gyms")
public class GymController {

    @Autowired
    GymRepository gymRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Gym getGymById(@PathVariable(value = "id") int id) throws Exception {
        try {
            return gymRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception("Gym not found");
        }
    }
}
