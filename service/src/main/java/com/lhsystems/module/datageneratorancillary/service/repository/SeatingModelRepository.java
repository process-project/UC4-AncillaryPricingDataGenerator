package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data repository used for save seating model entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface SeatingModelRepository extends CrudRepository<SeatingModel, Long> {

}

