package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "experts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Expert name cannot be null")
    @Size(min = 1, max = 50, message = "Expert name must be between 1 and 50 characters")
    @Column(name = "name")
    private String name;

}
