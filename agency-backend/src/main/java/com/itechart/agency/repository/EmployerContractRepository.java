package com.itechart.agency.repository;

import com.itechart.agency.entity.EmployerContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployerContractRepository extends JpaRepository<EmployerContract, Long>, JpaSpecificationExecutor<EmployerContract> {
    //мб надо findAllByIs_deletedIsFalse()
//    List<EmployerContract> findAllBy_deletedIsFalse();
}
