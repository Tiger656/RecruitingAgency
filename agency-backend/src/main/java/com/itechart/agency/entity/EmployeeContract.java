package com.itechart.agency.entity;

import com.itechart.agency.entity.lists.EmploymentType;
import com.itechart.agency.entity.lists.Feature;
import com.itechart.agency.entity.lists.Profession;
import com.itechart.agency.entity.lists.Status;
import com.itechart.agency.entity.location.Address;
import com.itechart.agency.entity.location.City;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "agency_employee_contract")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeContract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Agency id in employee contract cannot be null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private Agency agency;

    @NotNull(message = "Min salary in employee contract cannot be null")
    @Size(min = 1, max = 38, message = "Min salary in employee contract must be between 1 and 50 characters")
    @Column(name = "min_salary")
    private double min_salary;

    @NotNull(message = "Price usd in employee contract cannot be null")
    @Size(min = 1, max = 38, message = "Price usd in employee contract must be between 1 and 50 characters")
    @Column(name = "price_usd")
    private double price_usd;

    @NotNull(message = "Compensation in employee contract cannot be null")
    @Size(min = 1, max = 38, message = "Compensation in employee contract must be between 1 and 50 characters")
    @Column(name = "compensation")
    private double compensation;

    @NotNull(message = "Creation date in employee contract cannot be null")
    @Column(name = "creation_date")
    private Date creation_date;

    @NotNull(message = "End date in employee contract cannot be null")
    @Column(name = "end_date")
    private Date end_date;

    @NotNull(message = "Field is_deleted in employee contract cannot be null")
    @Column(name = "is_deleted")
    private boolean is_deleted;

    @NotNull(message = "Employee name cannot be null")
    @Size(min = 1, max = 50, message = "Employee name must be between 1 and 50 characters")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Employee surname cannot be null")
    @Size(min = 1, max = 50, message = "Employee surname must be between 1 and 50 characters")
    @Column(name = "surname")
    private String surname;

    @NotNull(message = "Employee experience years cannot be null")
    @Size(min = 1, max = 3, message = "Employee experience years must be between 1 and 3 characters")
    @Column(name = "experience_years")
    private int experience_years;

    @NotNull(message = "Employee birth date cannot be null")
    @Column(name = "birth_date")
    private Date birth_date;

    //notnull
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    //notnull
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull(message = "Employee's passport data cannot be null")
    @Size(min = 1, max = 50, message = "Employee's passport data must be between 1 and 50 characters")
    @Column(name = "passport")
    private String passport;

    //не нужное поле
    @NotNull(message = "Employee's email cannot be null")
    @Size(min = 1, max = 50, message = "Employee's email must be between 1 and 50 characters")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Employee's telephone number cannot be null")
    @Size(min = 1, max = 50, message = "Employee's telephone number must be between 1 and 50 characters")
    @Column(name = "telephone_number")
    private String telephone_number;

    @NotNull(message = "Profession in contract cannot be null")
    @ManyToOne
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    private Profession profession;


    @NotNull(message = "Account usd in employee contract cannot be null")
    @Column(name = "account_usd")
    private double account_usd;

    @NotNull(message = "Contract status cannot be null")
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    /* @OneToOne
    @JoinColumn(name = "agency_employee_contract_id", referencedColumnName = "id")
    private EmployeeContract employeeContract;
    @OneToOne
    @JoinColumn(name = "employee_type_id", referencedColumnName = "id")
    private EmploymentType employmentType;*/

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_employment_type", joinColumns = {
            @JoinColumn(name = "agency_employee_contract_id")}, inverseJoinColumns = {
            @JoinColumn(name = "employee_type_id")})
    private List<EmploymentType> employmentTypes;

    /*@ManyToOne
    @JoinColumn(name = "employee_contract_id")
    private EmployeeContract employeeContract;
    @OneToOne
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    private Feature feature;*/

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_contract_feature", joinColumns = {
            @JoinColumn(name = "employee_contract_id")}, inverseJoinColumns = {
            @JoinColumn(name = "feature_id")})
    private List<Feature> features;

}
