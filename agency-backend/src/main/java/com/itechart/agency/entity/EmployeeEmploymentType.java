package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee_employment_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeEmploymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "agency_employee_contract_id", referencedColumnName = "id")
    private AgencyEmployeeContract agencyEmployeeContract;
    @OneToOne
    @JoinColumn(name = "employee_type_id", referencedColumnName = "id")
    private EmploymentType employmentType;

}
