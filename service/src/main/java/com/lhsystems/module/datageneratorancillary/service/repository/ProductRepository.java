package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save product entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}

