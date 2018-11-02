package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data repository used for save seating group entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Transactional
public interface SeatGroupRepository extends ServiceBaseRepository<SeatGroup> {

}


