package com.itechart.agency.controller;


import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.Employer;
import com.itechart.agency.entity.User;
import com.itechart.agency.repository.AgencyRepository;
import com.itechart.agency.repository.EmployerRepository;
import com.itechart.agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TestController {

private final EmployerRepository employerRep;
    @Autowired
private AgencyRepository agencyRepository;
    private final UserRepository userRepository;

    public TestController(UserRepository userRepository, EmployerRepository employerRep) {
        this.userRepository = userRepository;
        this.employerRep = employerRep;
    }



}
