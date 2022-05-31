package com.thesis.backend.repository;

import com.thesis.backend.entities.LeaveStatus;
import com.thesis.backend.entities.enums.ELeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Integer> {
    Optional<LeaveStatus> findByName(ELeaveStatus name);
}
