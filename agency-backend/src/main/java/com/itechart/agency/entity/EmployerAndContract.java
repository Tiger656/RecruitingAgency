package com.itechart.agency.entity;

import com.itechart.agency.dto.EmployerContractDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployerAndContract {
    private User user;
    private EmployerContractDto contract;
}
