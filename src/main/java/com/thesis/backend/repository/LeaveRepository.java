package com.thesis.backend.repository;

import com.thesis.backend.entities.leave.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    @Override
    @Query(value = "SELECT * FROM leave ORDER BY start_date DESC;", nativeQuery = true)
    List<Leave> findAll();

    // 1 - PENDING
    // 2 - APPROVED
    // 3 - REFUSED
    @Query(value = "SELECT * FROM leave WHERE leave.status = 2 AND leave.fk_employee_id = :id", nativeQuery = true)
    List<Leave> findApprovedLeaveReq(@Param("id") int id);
}
