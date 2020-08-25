package com.itechart.agency.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private Long companyId;


    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column
    @NotNull
    private Boolean isDeleted;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<UserRole> userRoles;

}
