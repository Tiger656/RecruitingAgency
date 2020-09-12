package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employment_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmploymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
