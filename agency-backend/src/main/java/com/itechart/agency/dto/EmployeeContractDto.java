package com.itechart.agency.dto;

import com.itechart.agency.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class EmployeeContractDto {
    private Long id;

    private Long agencyId;
    private Long userId;
    private Long contractNumber;
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
    private String professionName; //
    private double accountUsd;
    private Long statusId;
   // private List<Long> employmentTypesIds;
}
