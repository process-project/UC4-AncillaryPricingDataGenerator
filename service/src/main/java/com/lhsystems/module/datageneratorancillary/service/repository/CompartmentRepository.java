package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save compartment entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface CompartmentRepository extends CrudRepository<Compartment, Long> {
}

