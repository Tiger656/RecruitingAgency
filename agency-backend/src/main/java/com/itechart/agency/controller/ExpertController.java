package com.itechart.agency.controller;

import com.itechart.agency.dto.ExpertForInterviewDto;
import com.itechart.agency.dto.converter.ExpertConvert;
import com.itechart.agency.service.ExpertService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/expert")
public class ExpertController {


    private final ExpertService expertService;

    public ExpertController(ExpertService expertService){
        this.expertService = expertService;
    }

    @GetMapping
    public List<ExpertForInterviewDto> getAll() {
        return expertService.findAll().stream().map(ExpertConvert::convertEntityToDto).collect(Collectors.toList());
    }
}
