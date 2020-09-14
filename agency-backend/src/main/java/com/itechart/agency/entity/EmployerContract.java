package com.itechart.agency.entity;

import com.itechart.agency.entity.lists.ContractType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "agency_employer_contracts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployerContract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Contract type cannot be null")
    @ManyToOne
    @JoinColumn(name = "contract_type_id", referencedColumnName = "id")
    private ContractType contractType;

    @NotNull(message = "File in contract cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private File file;

    @NotNull(message = "Daily payment in contract cannot be null")
    @Column(name = "daily_payment")
    private double daily_payment;

    @NotNull(message = "Contract creation date cannot be null")
    @Column(name = "contract_creation_date")
    private Date contract_creation_date;

    @NotNull(message = "Contract end date cannot be null")
    @Column(name = "contract_end_date")
    private Date contract_end_date;

    @NotNull(message = "Field is_suspended in contract cannot be null")
    @Column(name = "is_suspended")
    private boolean is_suspended;

    @NotNull(message = "Field is_deleted in contract cannot be null")
    @Column(name = "is_deleted")
    private boolean is_deleted;
}
