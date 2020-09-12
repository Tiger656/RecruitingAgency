package com.itechart.agency.entity.location;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",unique = true)
    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="countries_id",referencedColumnName = "id")
    private Country country;

    public Region(String name, Country country) {
    }
}
