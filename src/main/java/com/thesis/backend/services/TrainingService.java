package com.thesis.backend.services;

import com.thesis.backend.entities.Trainer;
import com.thesis.backend.entities.Training;
import com.thesis.backend.repository.TrainingRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    public void updateTraining(int trainingId, Training trainingDetails) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found with id " + trainingId));
        training.setPrincipalTrainer(trainingDetails.getPrincipalTrainer());
        training.setSecondaryTrainer((trainingDetails.getSecondaryTrainer()));
        training.setName(trainingDetails.getName());
        training.setStartTime(trainingDetails.getStartTime());
        training.setEndTime(trainingDetails.getEndTime());
        training.setDescription(trainingDetails.getDescription());
        training.setDayOfWeek(trainingDetails.getDayOfWeek());
        trainingRepository.save(training);
    }
}
