package com.itechart.agency.dto;

import com.itechart.agency.entity.Agency;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class AgencyTransactionDto {


    private Long id;
    private LocalDate date;
    private Double sum;
    private String agencyName;

    private Agency agency;
}
