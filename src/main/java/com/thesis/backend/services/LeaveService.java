package com.thesis.backend.services;


import com.thesis.backend.entities.Employee;
import com.thesis.backend.entities.Leave;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.LeaveRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    public void sendLeaveRequest(Leave leave) {
        for (Employee employee :
                employeesRepository.findAll()) {
            if (employee.getUserId() == leave.getEmployee().getUserId()) {
                leave.setEmployee(employee);
            }
        }
        leaveRepository.save(leave);
    }

    public void getRequests(){

    }
}
