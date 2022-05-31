package com.thesis.backend.controllers;

import com.thesis.backend.entities.Employee;
import com.thesis.backend.entities.TimeLog;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.TimeLogsRepository;
import com.thesis.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/timelogs")
public class TimeLogController {
    @Autowired
    TimeLogsRepository timeLogsRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<TimeLog> getAllTimeLogs() {
        return timeLogsRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public List<TimeLog> getTimeLogById(@PathVariable(value = "id") int userId) {
        List<TimeLog> allTimeLogs = timeLogsRepository.findAll();
        List<TimeLog> timeLogs = new ArrayList<>();
        for (Employee employee :
                employeesRepository.findAll()) {
            if (employee.getUserId() == userId) {
                for (TimeLog timelog :
                        allTimeLogs) {
                    if (timelog.getEmployee().getEmployeeId() == employee.getEmployeeId()) {
                        timeLogs.add(timelog);
                    }
                }
            }
        }
        return timeLogs;
    }

    @PostMapping("/addTimeLog")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> addTimeLog(@RequestBody TimeLog timeLog) {
        for (Employee employee :
                employeesRepository.findAll()) {
            if (employee.getUserId() == timeLog.getEmployee().getUserId()) {
                timeLog.setEmployee(employee);
            }
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timeLog.setTimestamp(timestamp);
        timeLogsRepository.save(timeLog);
        return ResponseEntity.ok(new MessageResponse("Time log added successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> deleteTimeLog(@PathVariable(value = "id") int id) {
        timeLogsRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Time log deleted successfully!"));
    }
}
