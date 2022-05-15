package com.thesis.backend.repository;

import com.thesis.backend.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

    @Override
    List<Employee> findAll();
}
