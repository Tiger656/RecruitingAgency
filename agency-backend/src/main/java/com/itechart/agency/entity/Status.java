package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
}
