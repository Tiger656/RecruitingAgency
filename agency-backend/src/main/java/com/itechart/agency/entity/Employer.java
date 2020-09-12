package com.itechart.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itechart.agency.entity.location.Address;
import com.itechart.agency.entity.location.City;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    //    Correct

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private Agency agency;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_agency_contract_id", referencedColumnName = "id")
    private AgencyEmployerContract agencyEmployerContract;

    @OneToMany(mappedBy = "employer")
    @JsonIgnore
    private List<EmployerApplication> applications;

}
