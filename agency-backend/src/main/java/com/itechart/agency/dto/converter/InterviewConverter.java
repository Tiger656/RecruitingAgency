package com.itechart.agency.dto.converter;

import com.itechart.agency.dto.EmployeeContractDto;
import com.itechart.agency.dto.InterviewDto;
import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.EmployeeContract;
import com.itechart.agency.entity.Interview;
import com.itechart.agency.entity.InterviewQuestion;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InterviewConverter {
    public static InterviewDto convertEntityToDto(EmployeeContract entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, InterviewDto.class);
    }

   /* public static Interview convertDtoToEntity(InterviewQuestion dto) {
        *//*ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, EmployeeContract.class);*//*
        return null;
        //Interview interview = new Interview(null, new Agency(),LocalDate.parse("1990-04-08", DateTimeFormatter.ofPattern( "yyy-MM-dd" )),  "WAITING_APPROVING_EXPERT_AND_EMPLOYEE", "mamanger comment", "expert_comment");
    }*/

    public static Interview toEntity(InterviewDto interviewDto) {
        ModelMapper mapper = new ModelMapper();
        return Objects.isNull(interviewDto) ? null : mapper.map(interviewDto, Interview.class);
    }
}
