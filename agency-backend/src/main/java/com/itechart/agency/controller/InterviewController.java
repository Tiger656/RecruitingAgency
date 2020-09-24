package com.itechart.agency.controller;

import com.itechart.agency.dto.EmployerContractDto;
import com.itechart.agency.dto.InterviewDto;
import com.itechart.agency.dto.converter.InterviewConverter;
import com.itechart.agency.entity.Interview;
import com.itechart.agency.service.InterviewService;
import com.itechart.agency.service.impl.EmployerContractServiceImpl;
import com.itechart.agency.service.impl.InterviewServiceImpl;
import org.modelmapper.ModelMapper;
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
//?? надо менять url
@RequestMapping("/interview")
public class InterviewController {
    private InterviewServiceImpl interviewService;
    private ModelMapper modelMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerContractController.class);

    @Autowired
    public InterviewController(InterviewServiceImpl interviewService, ModelMapper modelMapper) {
        this.interviewService = interviewService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> createInterview(@RequestBody InterviewDto interviewDto){
        System.out.println(interviewDto);
        Interview interview = InterviewConverter.toEntity(interviewDto);
        System.out.println(interview);
        Long id = interviewService.create(interview);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
