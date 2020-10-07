package com.itechart.agency.controller;

import com.itechart.agency.dto.BusyHoursDto;
import com.itechart.agency.dto.InterviewGetDto;
import com.itechart.agency.dto.InterviewSaveDto;
import com.itechart.agency.dto.converter.InterviewGetConverter;
import com.itechart.agency.dto.converter.InterviewSaveConverter;
import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.Interview;
import com.itechart.agency.service.impl.AgencyServiceImpl;
import com.itechart.agency.service.impl.InterviewServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/interview")
public class InterviewController {
    private InterviewServiceImpl interviewService;
    private AgencyServiceImpl agencyService;
    private ModelMapper modelMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerContractController.class);

    @Autowired
    public InterviewController(InterviewServiceImpl interviewService, ModelMapper modelMapper, AgencyServiceImpl agencyService) {
        this.interviewService = interviewService;
        this.modelMapper = modelMapper;
        this.agencyService = agencyService;
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping
    public ResponseEntity<?> createInterview(@RequestBody InterviewSaveDto interviewSaveDto) {
        System.out.println(interviewSaveDto);
        Interview interview = InterviewSaveConverter.toEntity(interviewSaveDto);
        System.out.println(interview);
        Long id = interviewService.create(interview);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/get-time/{agencyId}/{expertId}/{year}/{month}/{day}")
    public ResponseEntity<?> getBusyTimeForManager(@PathVariable("agencyId") Long agencyId,
                                                   @PathVariable("expertId") Long expertId,
                                                   @PathVariable("year") Integer year,
                                                   @PathVariable("month") Integer month,
                                                   @PathVariable("day") Integer day) {
        LOGGER.info("REST request. Path:/interview/get-time method: GET.getBusyTimeForManager");
        BusyHoursDto busyHours = interviewService.getBusyHours(agencyId, expertId, year, month, day);
        return new ResponseEntity<BusyHoursDto>(busyHours, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{agencyId}/{managerId}")
    public ResponseEntity<List<InterviewGetDto>> getInterviewsForManager(@PathVariable("agencyId") Long agencyId,
                                                                         @PathVariable("managerId") Long managerId
    ) {
        LOGGER.info("REST request. Path:/interview method: GET.getInterviewsForManager");
        List<Interview> interviews = interviewService.findAllByAgencyAndManager(agencyId, managerId);
        //List<InterviewSaveDto> interviewSaveDtos = new ArrayList<>();
        //interviews.forEach(interview -> interviewSaveDtos.add(InterviewSaveConverter.convertEntityToDto(interview)));
        List<InterviewGetDto> interviewGetDtos = new ArrayList<>();
        interviews.forEach(interview -> interviewGetDtos.add(InterviewGetConverter.convertEntityToDto(interview)));
        return new ResponseEntity<>(interviewGetDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{agencyId}")
    public ResponseEntity<List<InterviewGetDto>> getAllAgencyInterviewsForManager(@PathVariable("agencyId") Long agencyId) {
        LOGGER.info("REST request. Path:/interview method: GET.getAllAgencyInterviewsForManager");
        List<Interview> interviews = interviewService.findAllByAgency(agencyId);
        //List<InterviewSaveDto> interviewSaveDtos = new ArrayList<>();
        //interviews.forEach(interview -> interviewSaveDtos.add(InterviewSaveConverter.convertEntityToDto(interview)));
        List<InterviewGetDto> interviewGetDtos = new ArrayList<>();
        interviews.forEach(interview -> interviewGetDtos.add(InterviewGetConverter.convertEntityToDto(interview)));
        return new ResponseEntity<>(interviewGetDtos, HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('EXPERT')")
    @GetMapping("/{expertId}")
    public ResponseEntity<List<InterviewGetDto>> getInterviewForExpert(@PathVariable("agencyId") Long agencyId,
                                                                       @PathVariable("expertId") Long expertId,
                                                                       @PathVariable("statusId") Long statusId
    ) {
        LOGGER.info("REST request. Path:/interview method: GET.getInterviewForExpert");
        List<Interview> interviews = interviewService.findAllByAgencyAndExpertAndInterviewStatus(agencyId, expertId, statusId);
        List<InterviewGetDto> interviewGetDtos = new ArrayList<>();
        interviews.forEach(interview -> interviewGetDtos.add(InterviewGetConverter.convertEntityToDto(interview)));
        return new ResponseEntity<>(interviewGetDtos, HttpStatus.OK);
    }
}
