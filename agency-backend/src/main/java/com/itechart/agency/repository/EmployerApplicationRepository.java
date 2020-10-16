package com.itechart.agency.repository;

import com.itechart.agency.entity.EmployerApplication;
import com.itechart.agency.entity.EmployerContract;
import com.itechart.agency.entity.lists.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerApplicationRepository extends JpaRepository<EmployerApplication, Long>, JpaSpecificationExecutor<EmployerApplication> {

    List<EmployerApplication> findByStatus(Status status);

    @Query(value = "SELECT * FROM public.employer_application WHERE employer_id = :employerId AND is_deleted = false", nativeQuery = true)
    List<EmployerApplication> findByEmployerIdAndDeletedFalse(@Param("employerId") Long id);

    @Query(value = "SELECT * FROM public.employer_application WHERE is_deleted = :false", nativeQuery = true)
    List<EmployerApplication> findByDeletedFalse();

    @Query(value = "SELECT name FROM public.agency_contract_types WHERE id=(SELECT contract_type_id FROM public.agency_employer_contracts WHERE id = (SELECT employer_agency_contract_id from public.employer " +
            "WHERE id = (SELECT employer_id FROM public.employer_application WHERE id= :appId )))", nativeQuery = true)
    String getContractTypeNameForApplication(@Param("appId") Long id);

    List<EmployerApplication> findByAgencyIdAndIsDeletedFalse(Long agencyId);
}
