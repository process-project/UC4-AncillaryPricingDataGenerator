package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save tariff entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface ServiceOrderRepository
        extends CrudRepository<ServiceOrder, Long> {
}

