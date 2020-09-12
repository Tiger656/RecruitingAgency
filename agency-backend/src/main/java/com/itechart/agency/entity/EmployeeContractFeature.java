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

public class EmployeeContractFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_contract_id")
    private AgencyEmployeeContract agencyEmployeeContract;
    @OneToOne
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    private Feature feature;
}
