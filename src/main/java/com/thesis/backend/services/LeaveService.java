package com.thesis.backend.services;


import com.thesis.backend.entities.Employee;
import com.thesis.backend.entities.leave.Leave;
import com.thesis.backend.entities.leave.LeaveStatus;
import com.thesis.backend.entities.enums.ELeaveStatus;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.LeaveRepository;
import com.thesis.backend.repository.LeaveStatusRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    LeaveStatusRepository leaveStatusRepository;

//    public List<Leave> getAllRequests(){
//        List<Leave> leaveList = new ArrayList<>();
//        for (Leave leave:
//             leaveRepository.findAll()) {
//            for (Employee employee:
//                 employeesRepository.findAll()) {
//                if(leave.getEmployee().getEmployeeId() == employee.getEmployeeId()){
//                    leave.setEmployee(employee);
//                    leaveRepository.findAll();
//                    leaveList.add(leave);
//                }
//            }
//        }
//        return leaveList;
//    }

    public void sendLeaveRequest(Leave leave) {
        for (Employee employee :
                employeesRepository.findAll()) {
            if (employee.getUserId() == leave.getEmployee().getUserId()) {
                leave.setEmployee(employee);
            }
        }
        LeaveStatus leaveStatus = leaveStatusRepository.findByName(ELeaveStatus.PENDING)
                .orElseThrow(() -> new RuntimeException("Error: Leave status is not found."));
        leave.setStatus(leaveStatus);
        leaveRepository.save(leave);
    }

    public List<Leave> getRequests(int id) {
        List<Leave> allLeaveRequests = leaveRepository.findAll();
        List<Leave> leaveRequests = new ArrayList<>();
        for (Employee employee :
                employeesRepository.findAll()) {
            if (employee.getUserId() == id) {
                for (Leave leave :
                        allLeaveRequests) {
                    if (leave.getEmployee().getEmployeeId() == employee.getEmployeeId()) {
                        leaveRequests.add(leave);
                    }
                }
            }
        }
        return leaveRequests;
    }

    public void updateLeave(int leaveId, Leave leaveDetails) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found with id " + leaveId));
        leave.setStartDate(leaveDetails.getStartDate());
        leave.setEndDate(leaveDetails.getEndDate());
        leave.setLeaveType(leaveDetails.getLeaveType());
        leave.setReason(leaveDetails.getReason());
        leave.setStatus(leaveDetails.getStatus());
//        leave.setEmployee(leaveDetails.getEmployee());
        for (Employee employee :
                employeesRepository.findAll()) {
            if (employee.getEmployeeId() == leave.getEmployee().getEmployeeId()) {
                leave.setEmployee(employee);
            }
        }
        leaveRepository.save(leave);
    }

    public List<Leave> getApprovedReq(int userId){
        List<Leave> leaveRequests = new ArrayList<>();
        for (Employee employee :
                employeesRepository.findAll()) {
            if (employee.getUserId() == userId) {
                for (Leave leave :
                        leaveRepository.findApprovedLeaveReq(employee.getEmployeeId())) {
                    if (leave.getEmployee().getEmployeeId() == employee.getEmployeeId()) {
                        leaveRequests.add(leave);
                    }
                }
            }
        }
        return leaveRequests;
    }
}
