package com.itechart.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itechart.agency.entity.location.Address;
import com.itechart.agency.entity.location.City;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "agency_employee_contract")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgencyEmployeeContract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private Agency agency;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    private String passport;
    private String email;
    private String telephone_number;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    private Profession profession;
    private double account_usd;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
    @OneToMany(mappedBy = "agencyEmployeeContract")
    @JsonIgnore
    private List<EmployeeContractFeature> employeeContractFeatureList;

}
