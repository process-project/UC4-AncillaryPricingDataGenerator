package com.lhsystems.module.data.generator.ancillary.repository;

import com.lhsystems.module.data.generator.ancillary.data.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}

