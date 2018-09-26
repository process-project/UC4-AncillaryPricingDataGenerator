package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save tariff entities
 */
@Repository
public interface TariffRepository extends CrudRepository<Tariff, Long> {
}

