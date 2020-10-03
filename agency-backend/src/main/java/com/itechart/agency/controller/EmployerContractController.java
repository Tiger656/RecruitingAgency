package com.itechart.agency.controller;

import com.itechart.agency.dto.EmployerContractDto;
import com.itechart.agency.service.impl.EmployerContractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employer-contract")
public class EmployerContractController {
    private final EmployerContractServiceImpl employerContractService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerContractController.class);

    @Autowired
    public EmployerContractController(final EmployerContractServiceImpl employerContractService) {
        this.employerContractService = employerContractService;
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAuthority('SECRETARY') or hasAuthority('MANAGER')or hasAuthority('EMPLOYER')")
    @GetMapping("/{id}")
    public EmployerContractDto getOneEmployerContract(@PathVariable("id") Long id) {
        LOGGER.info("REST request. Path:/employer-contract/{} method: GET.", id);
        return employerContractService.findById(id);
    }


   /* @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit")
    public ResponseEntity<?> editEmployerContract(final @Valid @RequestBody EmployerContractDto employerContractDto) {
        LOGGER.info("REST request. Path:/employer-contract/edit method: POST. employer contract: {}", employerContractDto);
        employerContractService.update(employerContractDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/create")
    public ResponseEntity<?> createEmployerContract(final @Valid @RequestBody EmployerContractDto employerContractDto) {
        LOGGER.info("REST request. Path:/employer-contract/create method: POST. employer contract: {}", employerContractDto);
        employerContractService.create(employerContractDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEmployerContract(@PathVariable("id") Long id) {
        LOGGER.info("REST request. Path:/employer-contract/{} method: DELETE.", id);
        employerContractService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAuthority('SECRETARY') or hasAuthority('MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployerContracts() {
        LOGGER.info("REST request. Path:/employer-contract/all method: GET.");
        final List<EmployerContractDto> employerContractDtos = employerContractService.findAll();
        return new ResponseEntity<>(employerContractDtos, HttpStatus.OK);
    }*/

}
