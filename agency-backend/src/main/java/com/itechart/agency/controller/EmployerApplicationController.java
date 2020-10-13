package com.itechart.agency.controller;

import com.itechart.agency.dto.EmployerApplicationDto;
import com.itechart.agency.dto.EmployerApplicationForManagerDto;
import com.itechart.agency.service.impl.EmailServiceImpl;
import com.itechart.agency.service.impl.EmployerApplicationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employerApplication")
public class EmployerApplicationController {
    private final EmployerApplicationServiceImpl employerApplicationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerApplicationController.class);

    @Autowired
    public EmployerApplicationController(final EmployerApplicationServiceImpl employerApplicationService) {
        this.employerApplicationService = employerApplicationService;
    }


    @PreAuthorize("hasAuthority('SECRETARY')")
    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(final @Valid @RequestBody String[] email) throws MessagingException {
        LOGGER.info("REST request. Path:/employerApplication/sendEmail method: POST. email: {}", "to " + email[0] + ", message: " + email[2]);
        EmailServiceImpl.send(email[0], email[1], email[2]);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('SECRETARY')or hasAuthority('MANAGER')or hasAuthority('EMPLOYER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneEmployerApplication(@PathVariable("id") Long id) {
        LOGGER.info("REST request. Path:/employerApplication/{} method: GET.", id);
        final EmployerApplicationDto employerApplicationDto = employerApplicationService.findById(id);
        return Objects.isNull(employerApplicationDto) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok().body(employerApplicationDto);
    }


    @PreAuthorize("hasAuthority('SECRETARY')")
    @PutMapping("/edit")
    public ResponseEntity<?> editEmployerApplication(final @Valid @RequestBody EmployerApplicationDto employerApplicationDto) {
        LOGGER.info("REST request. Path:/employerApplication/edit method: POST. employer: {}", employerApplicationDto);
        employerApplicationService.update(employerApplicationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('SECRETARY') or hasAuthority('EMPLOYER')")
    @PutMapping("/create")
    public ResponseEntity<?> createEmployerApplication(final @Valid @RequestBody EmployerApplicationDto employerApplicationDto) {
        LOGGER.info("REST request. Path:/employerApplication/create method: POST. employer: {}", employerApplicationDto);
        employerApplicationService.create(employerApplicationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //нельзя удалять при статусе "рассматривается"
    @PreAuthorize("hasAuthority('SECRETARY') or hasAuthority('EMPLOYER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEmployerApplication(@PathVariable("id") Long id) {
        LOGGER.info("REST request. Path:/employerApplication/{} method: DELETE.", id);
        employerApplicationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/all-for-manager/{agencyId}")
    public ResponseEntity<?> getAllEmployerApplicationsForManager(@PathVariable Long agencyId) {
        LOGGER.info("REST request. Path:/employerApplication/all-for-manager method: GET.");
        final List<EmployerApplicationForManagerDto> employerAppDtos = employerApplicationService.findAllForManagerByAgency(agencyId);
        return new ResponseEntity<>(employerAppDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SECRETARY') or hasAuthority('MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployerApplications() {
        LOGGER.info("REST request. Path:/employerApplication/all method: GET.");
        final List<EmployerApplicationDto> employerApplicationDtos = employerApplicationService.findAll();
        return new ResponseEntity<>(employerApplicationDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SECRETARY')")
    @PutMapping("/change-status/{id}/{statusNew}")
    public EmployerApplicationDto changeEmployerApplicationStatus(@PathVariable("id") Long id, @PathVariable("statusNew") String statusName) {
        LOGGER.info("REST request. Path:/employerApplication/change-status/{id}/{statusId} method: PUT. employerId and statusName: {}", id + ", " + statusName);
        System.out.println(statusName);
        if (statusName.contains("-")) {
            String status = statusName.replace('-', ' ');
            return employerApplicationService.changeApplicationStatus(id, status);
        } else return employerApplicationService.changeApplicationStatus(id, statusName);
    }

    @PreAuthorize("hasAuthority('SECRETARY') or hasAuthority('MANAGER')")
    @GetMapping("/getAllByStatus/{status}")
    public List<EmployerApplicationDto> getAllEmployerApplicationByStatus(@PathVariable("status") String status) {
        LOGGER.info("REST request. Path:/employerApplication/getAllByStatus/{} method: GET.", status);
        System.out.println(status);
        if (status.contains("-")) {
            String statusN = status.replace('-', ' ');
            return employerApplicationService.getApplicationsByStatus(statusN);
        } else return employerApplicationService.getApplicationsByStatus(status);
    }
}
