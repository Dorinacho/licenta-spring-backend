package com.thesis.backend.controllers;

import com.thesis.backend.entities.User;
import com.thesis.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // Injecting Dependencies
    @Autowired
    private UserRepository userRepo;

    // Defining the function to handle the GET route to fetch user information of the authenticated user
    @GetMapping("/info")
    public User getUserDetails() {
        // Retrieve username from the Security Context
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Fetch and return user details
        return userRepo.findByUsername(username).get();
    }

}
