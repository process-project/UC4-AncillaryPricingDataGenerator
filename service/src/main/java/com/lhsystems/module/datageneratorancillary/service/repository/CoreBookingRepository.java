package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
<<<<<<< Upstream, based on lmuGitlab/topic/EUPLSY-85
 * Spring Data repository used for saving core bookings entities.
=======
 * Spring Data repository used for saving core booking entities.
>>>>>>> bede2e0 EUPLSY-85: Add additional configuration
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface CoreBookingRepository
extends CrudRepository<CoreBooking, Long> {
}
