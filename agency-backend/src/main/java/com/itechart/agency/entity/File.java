package com.itechart.agency.entity;
/*
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "File name cannot be null")
    @Size(min = 1, max = 50, message = "File name must be between 1 and 50 characters")
    @Column(name = "name")
    private String name;

    @NotNull(message = "File path cannot be null")
    @Size(min = 1, max = 50, message = "File path must be between 1 and 50 characters")
    @Column(name = "file_path")
    private String file_path;

    @NotNull(message = "Creation date cannot be null")
    @Column(name = "creation_date")
    private Date creation_date;

    @NotNull(message = "Field is_deleted in file cannot be null")
    @Column(name = "is_deleted")
    private boolean is_deleted;
}
*/