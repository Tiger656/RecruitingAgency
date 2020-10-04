package com.itechart.agency.controller;

import com.itechart.agency.dto.EmployeeContractDto;
import com.itechart.agency.service.impl.EmployeeContractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employeeContract")
public class EmployeeContractController {
    private final EmployeeContractServiceImpl employeeContractService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeContractController.class);

    @Autowired
    public EmployeeContractController(final EmployeeContractServiceImpl employeeContractService) {
        this.employeeContractService = employeeContractService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARY') or hasAuthority('MANAGER')or hasAuthority('EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneEmployeeContract(@PathVariable("id") Long id) {
        LOGGER.info("REST request. Path:/employee-contract/{} method: GET.", id);
        final EmployeeContractDto employeeContractDto = employeeContractService.findById(id);
        return Objects.isNull(employeeContractDto) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok().body(employeeContractDto);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit")
    public ResponseEntity<?> editEmployeeContract(final @Valid @RequestBody EmployeeContractDto employeeContractDto) {
        LOGGER.info("REST request. Path:/employee-contract/edit method: POST. employee contract: {}", employeeContractDto);
        employeeContractService.update(employeeContractDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/create")
    public ResponseEntity<?> createEmployeeContract(final @Valid @RequestBody EmployeeContractDto employeeContractDto) {
        LOGGER.info("REST request. Path:/employee-contract/create method: POST. employee contract: {}", employeeContractDto);
        employeeContractService.create(employeeContractDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEmployeeContract(@PathVariable("id") Long id) {
        LOGGER.info("REST request. Path:/employee-contract/{} method: DELETE.", id);
        employeeContractService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARY') or hasAuthority('MANAGER')")
    @GetMapping("/all")
    public List<EmployeeContractDto> getAllEmployeeContracts() {
        LOGGER.info("REST request. Path:/employee-contract/all method: GET.");
        return employeeContractService.findAll();
    }

}
