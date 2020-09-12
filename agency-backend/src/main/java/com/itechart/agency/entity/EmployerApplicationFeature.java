package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employer_application_feature")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployerApplicationFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employer_application_id")
    private EmployerApplication employerApplication;
    @OneToOne
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    private Feature feature;
}
