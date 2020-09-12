package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "agency_contract_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgencyContractType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

}
