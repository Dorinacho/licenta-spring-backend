package com.thesis.backend.services;

import com.thesis.backend.entities.Employee;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeService {
    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    UserRepository userRepository;

    public void addEmployee(Employee employee){
        String username = employee.getFirstName() + "." + employee.getLastName();
        int userId = userRepository.findByUsername(username).get().getId();
        employee.setUserId(userId);
        employeesRepository.save(employee);
    }

    public void updateEmployee(int id, Employee employeeDetails){
        Employee employee = employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setPhone(employeeDetails.getPhone());
        employee.setGym(employeeDetails.getGym());
        employee.setUserId(employeeDetails.getUserId());
        employeesRepository.save(employee);
    }
}
