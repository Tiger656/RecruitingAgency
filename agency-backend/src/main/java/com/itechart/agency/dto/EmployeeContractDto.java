package com.itechart.agency.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class EmployeeContractDto {
    private Long id;

    private Long agencyId;
    private double minSalary;
    private double priceUsd;
    private double compensation;
    private Date creationDate;
    private Date endDate;
    private boolean isDeleted;
    private String name;
    private String surname;
    private int experienceYears;
    private Date birthDate;
    private Long cityId;
    private Long addressId;
    private String passport;
    private String email;
    private String telephoneNumber;
    private Long professionId;
    private double accountUsd;
    private Long statusId;
    private List<Long> employmentTypesIds;
    private List<Long> featuresIds;
}
