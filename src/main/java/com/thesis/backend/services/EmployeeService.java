package com.thesis.backend.services;

import com.thesis.backend.entities.Employee;
import com.thesis.backend.entities.Gym;
import com.thesis.backend.repository.EmployeesRepository;
import com.thesis.backend.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeService {
   EntityManagerFactory factory = Persistence.createEntityManagerFactory("Gym_JPA");

   EntityManager entityManager = factory.createEntityManager();
    Employee employee
}
