package com.itechart.agency.repository;

import com.itechart.agency.entity.EmployeeContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeContractRepository extends JpaRepository<EmployeeContract, Long> {
    @Query(value = "SELECT * FROM public.agency_employee_contracts WHERE is_deleted = :false", nativeQuery = true)
    List<EmployeeContract> findAllByIs_deletedIsFalse();

    List<EmployeeContract> findByAgencyIdAndIsDeletedFalse(Long agencyId);

    /*List<EmployeeContract> findAllByAgencyIdAndByIs_deletedIsFalse();*/
}
