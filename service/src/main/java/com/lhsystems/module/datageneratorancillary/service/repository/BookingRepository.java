package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Booking;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for saving booking entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface BookingRepository
extends CrudRepository<Booking, Long> {
}
