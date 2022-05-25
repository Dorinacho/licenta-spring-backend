package com.thesis.backend.controllers;

import com.thesis.backend.entities.User;
import com.thesis.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Injecting Dependencies
    @Autowired
    private UserRepository userRepository;

    // Defining the function to handle the GET route to fetch user information of the authenticated user
//    @GetMapping
//    public User getUserDetails() {
//        // Retrieve username from the Security Context
//        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // Fetch and return user details
//        return userRepository.findByUsername(username).get();
//    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
