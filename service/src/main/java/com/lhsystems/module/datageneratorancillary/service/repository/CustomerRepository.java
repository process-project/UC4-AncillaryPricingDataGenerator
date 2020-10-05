package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for saving customer entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

