package com.itechart.agency.entity;

import com.itechart.agency.entity.lists.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employer_application")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployerApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Agency for employer cannot be null")
    @ManyToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private Agency agency;
//-
    @NotNull(message = "Application number cannot be null")
    @Size(min = 1, max = 50, message = "Application number must be between 1 and 50 characters")
    @Column(name = "application_number")
    private String application_number;

    @NotNull(message = "Application date cannot be null")
    @Column(name = "application_date")
    private Date application_date;

    @NotNull(message = "Employer for application cannot be null")
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
//-
    @NotNull(message = "Application name cannot be null")
    @Size(min = 1, max = 100, message = "Application name must be between 1 and 100 characters")
    @Column(name = "application_name")
    private String application_name;

    @NotNull(message = "Profession in application cannot be null")
    @ManyToOne
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    private Profession profession;

    @NotNull(message = "Salary in application cannot be null")
    @Size(min = 1, max = 38, message = "Salary in application must be between 1 and 38 characters")
    @Column(name = "salary")
    private double salary;
//рабочий график
    @NotNull(message = "Employment type in application cannot be null")
    @ManyToOne
    @JoinColumn(name = "employment_type_id", referencedColumnName = "id")
    private EmploymentType employmentType;

    @Size(max = 100, message = "Expert name must be shorter than 100")
    @Column(name = "expert_personal_name")
    private String expert_personal_name;

    @NotNull(message = "Application creation date cannot be null")
    @Column(name = "creation_date")
    private Date creation_date;

    @NotNull(message = "Application end date cannot be null")
    @Column(name = "end_date")
    private Date end_date;

    @NotNull(message = "Application status cannot be null")
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;


//-
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "employer_application_feature", joinColumns = {
            @JoinColumn(name = "employer_application_id")}, inverseJoinColumns = {
            @JoinColumn(name = "feature_id")})
    private List<Feature> features;
}
