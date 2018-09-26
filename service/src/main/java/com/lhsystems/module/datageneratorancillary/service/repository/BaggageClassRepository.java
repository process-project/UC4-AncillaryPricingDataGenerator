package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for save baggage class entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface BaggageClassRepository extends CrudRepository<BaggageClass, Long> {

}
