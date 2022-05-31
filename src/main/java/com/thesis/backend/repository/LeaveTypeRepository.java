package com.thesis.backend.repository;

import com.thesis.backend.entities.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {

    @Override
    List<LeaveType> findAll();

}

