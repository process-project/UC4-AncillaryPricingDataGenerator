package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save baggage size entities
 */
@Repository
public interface BaggageSizeRepository extends CrudRepository<BaggageSize, Long> {
}
