package com.itechart.agency.entity;

import com.sun.istack.NotNull;
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
public class Role implements BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy="role", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<UserRole> userRoles;

}
