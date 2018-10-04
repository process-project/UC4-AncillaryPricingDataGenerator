package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageSelection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for saving baggage selection entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface BaggageSelectionRepository
extends CrudRepository<BaggageSelection, Long> {
}
