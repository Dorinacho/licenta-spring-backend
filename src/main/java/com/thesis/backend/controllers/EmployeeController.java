package com.thesis.backend.controllers;

import com.thesis.backend.entities.Employee;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.UserRepository;
import com.thesis.backend.services.EmployeeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Employee getEmployeeById(@PathVariable(value = "id") int id) throws Exception {
        try {
            return employeesRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception("Employee not found");
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") int employeeId, @RequestBody @NotNull Employee employeeDetails) {
        employeeService.updateEmployee(employeeId, employeeDetails);
        return ResponseEntity.ok(new MessageResponse("Employee edited successfully!"));
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Employee> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @PostMapping("/addEmployee")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok(new MessageResponse("Employee added successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") int id) {
        employeesRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Employee deleted successfully!"));
    }
}
