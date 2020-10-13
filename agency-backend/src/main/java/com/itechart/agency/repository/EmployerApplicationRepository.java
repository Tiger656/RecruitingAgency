package com.itechart.agency.repository;

import com.itechart.agency.entity.EmployerApplication;
import com.itechart.agency.entity.lists.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerApplicationRepository extends JpaRepository<EmployerApplication, Long>, JpaSpecificationExecutor<EmployerApplication> {

    List<EmployerApplication> findByStatus(Status status);


    List<EmployerApplication> findByAgencyIdAndIsDeletedFalse(Long agencyId);
}
