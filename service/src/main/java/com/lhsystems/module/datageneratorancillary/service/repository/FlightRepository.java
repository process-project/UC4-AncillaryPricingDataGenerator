package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save flight entities
 */

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
