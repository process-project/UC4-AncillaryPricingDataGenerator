package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save seating group entities
 */
@Repository
public interface SeatingGroupRepository extends CrudRepository<SeatGroup, Long> {

}


