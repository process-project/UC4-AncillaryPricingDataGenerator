package com.lhsystems.module.data.generator.ancillary.repository;

import com.lhsystems.module.data.generator.ancillary.data.Compartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompartmentRepository extends CrudRepository<Compartment, Long> {
}

