package com.itechart.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itechart.agency.entity.location.Address;
import com.itechart.agency.entity.location.City;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    private City city;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "agency")
    @JsonIgnore
    private List<User> users;
    @OneToMany(mappedBy = "agency")
    @JsonIgnore
    private List<AgencyEmployeeContract> agencyEmployeeContracts;

}
