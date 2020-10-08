package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "agencies_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgencyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date")
    private Date date;


    @Column(name = "sum")
    private Integer sum;

    @NotNull(message = "Agency for user cannot be null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private Agency agency;


}
