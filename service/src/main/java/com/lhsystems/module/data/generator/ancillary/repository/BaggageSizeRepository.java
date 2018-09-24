package com.lhsystems.module.data.generator.ancillary.repository;

import com.lhsystems.module.data.generator.ancillary.data.BaggageSize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaggageSizeRepository extends CrudRepository<BaggageSize, Long> {
}
