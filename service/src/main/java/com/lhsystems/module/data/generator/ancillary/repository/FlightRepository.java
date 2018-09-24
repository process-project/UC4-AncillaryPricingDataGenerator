package com.lhsystems.module.data.generator.ancillary.repository;

import com.lhsystems.module.data.generator.ancillary.data.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
