package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save baggage class entities
 */

@Repository
public interface BaggageClassRepository
extends CrudRepository<BaggageClass, Long> {

}
