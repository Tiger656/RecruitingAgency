package com.itechart.agency.controller;


import com.itechart.agency.entity.Role;
import com.itechart.agency.entity.User;
import com.itechart.agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/posts/{email}")
    public List<Role> getAllPosts(@PathVariable(name = "email") String email) {

        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not exist"));
        return user.getRoles();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/posts")
    public String getName() {

        return "Name";
    }
}
