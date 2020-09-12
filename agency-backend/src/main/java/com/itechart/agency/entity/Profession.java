package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "profession")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
