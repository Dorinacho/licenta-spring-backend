package com.thesis.backend.repository;

import com.thesis.backend.entities.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLogsRepository extends JpaRepository<TimeLog, Integer> {
    @Override
    @Query(value = "SELECT * FROM time_logs ORDER BY date DESC;", nativeQuery = true)
    List<TimeLog> findAll();
}
