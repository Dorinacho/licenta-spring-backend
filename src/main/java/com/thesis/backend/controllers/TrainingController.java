package com.thesis.backend.controllers;

import com.thesis.backend.entities.Trainer;
import com.thesis.backend.entities.Training;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.TrainingRepository;
import com.thesis.backend.services.TrainingService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082"})
@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TrainingService trainingService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    @PostMapping("/addTraining")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addTraining(@RequestBody Training training) {
        trainingRepository.save(training);
        return ResponseEntity.ok(new MessageResponse("Trainer added successfully!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateTraining(@PathVariable(value = "id") int trainerId, @RequestBody @NotNull Training training) {
        trainingService.updateTraining(trainerId, training);
        return ResponseEntity.ok(new MessageResponse("Trainer edited successfully!"));
    }
}
