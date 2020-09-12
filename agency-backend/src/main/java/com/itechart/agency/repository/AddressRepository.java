package com.itechart.agency.repository;

import com.itechart.agency.entity.location.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
