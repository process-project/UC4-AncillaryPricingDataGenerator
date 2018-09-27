package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save baggage pricing entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface BaggagePricingRepository extends CrudRepository<BaggagePricing, Long> {

}
