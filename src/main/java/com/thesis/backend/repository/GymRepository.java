package com.thesis.backend.repository;

import com.thesis.backend.entities.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {

    @Override
    List<Gym> findAll();

}
