package com.thesis.backend.services;

import com.thesis.backend.entities.Gym;
import com.thesis.backend.entities.Trainer;
import com.thesis.backend.entities.Training;
import com.thesis.backend.repository.GymRepository;
import com.thesis.backend.repository.TrainersRepository;
import com.thesis.backend.repository.TrainingRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    GymRepository gymRepository;

    public void updateTraining(int trainingId, Training trainingDetails) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found with id " + trainingId));
        training.setPrincipalTrainer(trainingDetails.getPrincipalTrainer());
        training.setSecondaryTrainer(trainingDetails.getSecondaryTrainer());
        training.setName(trainingDetails.getName());
        training.setStartTime(trainingDetails.getStartTime());
        training.setEndTime(trainingDetails.getEndTime());
        training.setDescription(trainingDetails.getDescription());
        training.setDayOfWeek(trainingDetails.getDayOfWeek());
//        for (Gym gym :
//                gymRepository.findAll()) {
//            if (trainingDetails.getPrincipalTrainer().getGym().getGymId() == gym.getGymId()) {
//                training.getPrincipalTrainer().setGym(gym);
//            }
//            if (trainingDetails.getSecondaryTrainer().getGym().getGymId() == gym.getGymId()) {
//                training.getSecondaryTrainer().setGym(gym);
//            }
//        }
//        for (Trainer trainer:
//             trainersRepository.findAll()) {
//            if (trainingDetails.getPrincipalTrainer().getTrainerId() == trainer.getTrainerId()){
//                training.setPrincipalTrainer(trainer);
//            }
//            if (trainingDetails.getSecondaryTrainer().getTrainerId() == trainer.getTrainerId()){
//                training.setSecondaryTrainer(trainer);
//            }
//        }
        trainingRepository.save(training);
    }
}
