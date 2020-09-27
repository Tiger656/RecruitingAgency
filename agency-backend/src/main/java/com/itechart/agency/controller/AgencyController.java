package com.itechart.agency.controller;

import com.itechart.agency.entity.Agency;
import com.itechart.agency.service.impl.AgencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/agencies")
public class AgencyController {
    @Autowired
    private AgencyServiceImpl agencyService;
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('SYSADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){

//        return new ResponseEntity<>(agencyService.findAll(), HttpStatus.OK);
        return new ResponseEntity<>("Ilya",HttpStatus.OK);
    }
}
