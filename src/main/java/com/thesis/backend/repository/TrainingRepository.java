package com.thesis.backend.repository;

import com.thesis.backend.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    @Override
    List<Training> findAll();
}
