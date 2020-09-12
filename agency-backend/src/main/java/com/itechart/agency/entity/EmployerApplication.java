package com.itechart.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employer_application")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployerApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private Agency agency;

    private String application_number;

    private Date application_date;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
    private String application_name;
    @OneToOne
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    private Profession profession;
    private double salary;
    @OneToOne
    @JoinColumn(name = "employment_type_id", referencedColumnName = "id")
    private EmploymentType employmentType;
    private String expert_personal_name;
    private Date creation_date;
    private Date end_date;
    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
    @OneToMany(mappedBy = "employerApplication")
    @JsonIgnore
    private List<EmployerApplicationFeature> employerApplicationFeatures;


}
