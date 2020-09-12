package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "features")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
