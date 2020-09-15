package com.itechart.agency.entity.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name",unique = true)
    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="countries_id",referencedColumnName = "id")
    private Country country;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<City> cities;

}
