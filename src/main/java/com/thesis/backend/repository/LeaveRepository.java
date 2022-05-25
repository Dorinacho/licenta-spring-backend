package com.thesis.backend.repository;

import com.thesis.backend.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    @Override
    List<Leave> findAll();
}
