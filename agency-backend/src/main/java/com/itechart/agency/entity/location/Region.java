//package com.itechart.agency.entity.location;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.List;
//
//@Entity
//@Table(name = "regions")
//@Getter
//@Setter
//@ToString
//public class Region {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @NotNull(message = "Region name cannot be null")
//    @Size(min = 1, max = 50, message = "Region name must be between 1 and 50 characters")
//    @Column(name = "name", unique = true)
//    private String name;
//
//    @NotNull(message = "Country for region cannot be null")
//    @ManyToOne
//    @JoinColumn(name = "countries_id", referencedColumnName = "id")
//    private Country country;
//
//    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<City> cities;
//
//    public Region() {
//    }
//}
