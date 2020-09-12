package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;


}
