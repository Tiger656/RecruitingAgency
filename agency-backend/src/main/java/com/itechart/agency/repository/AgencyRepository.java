package com.itechart.agency.repository;

import com.itechart.agency.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long> {
    Optional<Agency> findById(Long id);
    Optional<Agency> findByName(String name);

}
