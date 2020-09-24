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
    private double min_salary;
    private double price_usd;
    private double compensation;
    private Date creation_date;
    private Date end_date;
    private boolean is_deleted;
    private String name;
    private String surname;
    private int experience_years;
    private Date birth_date;
    private Long cityId;
    private Long addressId;
    private String passport;
    private String email;
    private String telephone_number;
    private Long professionId;
    private double account_usd;
    private Long statusId;
    private List<Long> employmentTypesIds;
    private List<Long> featuresIds;


    @Override
    public String toString() {
        return "EmployeeContractDto{" +
                ", agencyId=" + agencyId +
                ", min_salary=" + min_salary +
                ", price_usd=" + price_usd +
                ", compensation=" + compensation +
                ", creation_date=" + creation_date +
                ", end_date=" + end_date +
                ", is_deleted=" + is_deleted +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", experience_years=" + experience_years +
                ", birth_date=" + birth_date +
                ", cityId=" + cityId +
                ", addressId=" + addressId +
                ", passport='" + passport + '\'' +
                ", email='" + email + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                ", professionId=" + professionId +
                ", account_usd=" + account_usd +
                ", statusId=" + statusId +
                '}';
    }
}
