package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save airport entities
 */
@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {
}
