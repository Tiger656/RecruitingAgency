package com.itechart.agency.controller;

import com.itechart.agency.entity.lists.EmploymentType;
import com.itechart.agency.service.impl.EmploymentTypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employmentType")
public class EmploymentTypeController {
    private final EmploymentTypeServiceImpl employmentTypeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmploymentTypeController.class);

    @Autowired
    public EmploymentTypeController(final EmploymentTypeServiceImpl employmentTypeService) {
        this.employmentTypeService = employmentTypeService;
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAuthority('SECRETARY') or hasAuthority('MANAGER') or hasAuthority('EMPLOYER')")
    @GetMapping("/{id}")
    public EmploymentType getOneEmploymentType(@PathVariable("id") Long id) {
        LOGGER.info("REST request. Path:/employmentType/{} method: GET.", id);
        return employmentTypeService.findById(id);

    }

    @GetMapping("/all")
    public List<EmploymentType> getAllEmploymentTypes() {
        LOGGER.info("REST request. Path:/employmentType/all method: GET.");
        return employmentTypeService.findAll();
    }

}
