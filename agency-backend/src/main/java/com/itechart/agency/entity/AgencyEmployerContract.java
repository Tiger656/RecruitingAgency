package com.itechart.agency.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "agency_employer_contracts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgencyEmployerContract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_type_id",referencedColumnName = "id")
    private AgencyContractType agencyContractTypes;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id",referencedColumnName = "id")
    private File file ;
    private double daily_payment;
    private Date  contract_creation_date;
    private Date  contract_end_date;
    private boolean is_suspended;
    private boolean is_deleted;

}
