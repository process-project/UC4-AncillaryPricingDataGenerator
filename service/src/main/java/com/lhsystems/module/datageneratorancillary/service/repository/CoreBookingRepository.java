package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for saving core bookings entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface CoreBookingRepository
extends CrudRepository<CoreBooking, Long> {
}
