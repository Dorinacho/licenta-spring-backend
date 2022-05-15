package com.thesis.backend.controllers;

import com.thesis.backend.entities.Employee;
import com.thesis.backend.entities.Gym;
import com.thesis.backend.entities.User;
import com.thesis.backend.payload.response.MessageResponse;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.GymRepository;
import com.thesis.backend.repository.UserRepository;
import org.hibernate.Session;
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
    UserRepository userRepository;

    @Autowired
    GymRepository gymRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Employee> getAllEmployees() {
        return employeesRepository.findAll();
    }

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

        Employee employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + employeeId));
        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setPhone(employeeDetails.getPhone());
        employee.setGym(employeeDetails.getGym());
        employeesRepository.save(employee);
        return ResponseEntity.ok(new MessageResponse("Employee edited successfully!"));
    }

    @PostMapping("/addEmployee")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        String username = employee.getFirstName() + "." + employee.getLastName();
        int userId = userRepository.findByUsername(username).get().getId();

//        Gym gym = gymRepository.findById(employee.getGym().getGymId())
//                .orElseThrow(() -> new RuntimeException("Can't find gym with id -> " + employee.getGym().getGymId()));
//        gym.getEmployees().add(employee);
//        employeesRepository
//                .save(new Employee(employee.getEmployeeId(), employee.getGym(), user.getId(), employee.getFirstName(), employee.getLastName(), employee.getPhone(), employee.getEmail()));
//        gymRepository.save(gym);


        employee.setUserId(userId);
        employeesRepository.save(employee);
        return ResponseEntity.ok(new MessageResponse("Employee added successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") int id){
        employeesRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Employee deleted successfully!"));
    }
}
