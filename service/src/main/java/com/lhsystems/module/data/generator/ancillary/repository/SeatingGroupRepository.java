package com.lhsystems.module.data.generator.ancillary.repository;

import com.lhsystems.module.data.generator.ancillary.data.SeatGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatingGroupRepository extends CrudRepository<SeatGroup, Long> {

}


