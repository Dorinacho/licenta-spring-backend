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

//    @GetMapping("/principals")
////    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public List<Trainer> getPrincipalTrainers() {
//        return trainersRepository.findPrincipalTrainers();
//    }

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

//        Trainer trainer = trainersRepository.findById(trainerId)
//                .orElseThrow(() -> new RuntimeException("Trainer not found with id " + trainerId));
//        trainer.setEmail(trainerDetails.getEmail());
//        trainer.setLastName(trainerDetails.getLastName());
//        trainer.setFirstName(trainerDetails.getFirstName());
//        trainer.setPhone(trainerDetails.getPhone());
//        trainer.setGym(trainerDetails.getGym());
//        trainersRepository.save(trainer);
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
