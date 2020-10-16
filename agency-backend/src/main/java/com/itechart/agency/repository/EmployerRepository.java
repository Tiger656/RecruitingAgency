package com.itechart.agency.repository;

import com.itechart.agency.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {

    @Query(value = "SELECT * FROM public.employer WHERE user_id = :id", nativeQuery = true)
    Optional<Employer> findByUserId(@Param("id") Long id);

}
