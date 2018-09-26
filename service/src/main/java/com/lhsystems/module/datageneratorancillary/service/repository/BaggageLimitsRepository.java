package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save baggage limits entities
 */

@Repository
public interface BaggageLimitsRepository extends CrudRepository<BaggageLimits, Long> {

}
