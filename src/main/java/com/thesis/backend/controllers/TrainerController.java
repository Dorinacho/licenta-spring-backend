package com.thesis.backend.controllers;

import com.thesis.backend.entities.Employee;
import com.thesis.backend.entities.Trainer;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.TrainersRepository;
import com.thesis.backend.repository.UserRepository;
import com.thesis.backend.services.TrainerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082"})
@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainerService trainerService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Trainer> getAllTrainers() {
        return trainersRepository.findAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Trainer getTrainerById(@PathVariable(value = "id") int id) throws Exception {
        try {
            return trainersRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception("Employee not found");
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateTrainer(@PathVariable(value = "id") int trainerId, @RequestBody @NotNull Trainer trainerDetails) {
        trainerService.updateTrainer(trainerId, trainerDetails);
        return ResponseEntity.ok(new MessageResponse("Trainer edited successfully!"));
    }

    @PostMapping("/addTrainer")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addTrainer(@RequestBody Trainer trainer) {
//        String username = trainer.getFirstName() + "." + trainer.getLastName();
//        int userId = userRepository.findByUsername(username).get().getId();
//        trainer.setUserId(userId);
//        trainersRepository.save(trainer);
        trainerService.addTrainer(trainer);
        return ResponseEntity.ok(new MessageResponse("Trainer added successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteTrainer(@PathVariable(value = "id") int id) {
        trainersRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Trainer deleted successfully!"));
    }
}
