package com.itechart.agency.dto.converter;

import com.itechart.agency.dto.EmployerDto;
import com.itechart.agency.dto.ExpertForInterviewDto;
import com.itechart.agency.entity.Employer;
import com.itechart.agency.entity.Expert;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

public class ExpertConvert {

    public static ExpertForInterviewDto convertEntityToDto(Expert entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, ExpertForInterviewDto.class);
    }

    public static Employer convertDtoToEntity(ExpertForInterviewDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Employer.class);
    }
}
