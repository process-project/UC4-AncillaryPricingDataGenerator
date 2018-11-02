package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.ServiceSelection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for saveing service selection entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface ServiceSelectionRepository
extends CrudRepository<ServiceSelection, Long> {

}
