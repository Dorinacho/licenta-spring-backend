package com.thesis.backend.repository;

import com.thesis.backend.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainersRepository extends JpaRepository<Trainer, Integer> {

    @Override
    List<Trainer> findAll();

    @Query(value = "SELECT *" +
            "FROM trainers " +
            "JOIN trainings " +
            "ON trainings.fk_principal_trainer = trainers.trainer_id", nativeQuery = true)
    List<Trainer> findPrincipalTrainers();
}
