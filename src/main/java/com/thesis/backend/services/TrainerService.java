package com.thesis.backend.services;

import com.thesis.backend.entities.Trainer;
import com.thesis.backend.repository.TrainersRepository;
import com.thesis.backend.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@Service
public class TrainerService {

    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    UserRepository userRepository;

    public void updateTrainer(int trainerId, Trainer trainerDetails){
        Trainer trainer = trainersRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found with id " + trainerId));
        trainer.setEmail(trainerDetails.getEmail());
        trainer.setLastName(trainerDetails.getLastName());
        trainer.setFirstName(trainerDetails.getFirstName());
        trainer.setPhone(trainerDetails.getPhone());
        trainer.setGym(trainerDetails.getGym());
        trainersRepository.save(trainer);
    }

    public void addTrainer(Trainer trainer){
            String username = trainer.getFirstName() + "." + trainer.getLastName();
            int userId = userRepository.findByUsername(username).get().getId();
            trainer.setUserId(userId);
            trainersRepository.save(trainer);
    }
}
