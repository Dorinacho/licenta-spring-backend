package com.thesis.backend.repository;

import com.thesis.backend.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainersRepository extends JpaRepository<Trainer, Integer> {

    @Override
    List<Trainer> findAll();
}
