package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Donation;

import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for saving donation entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface DonationRepository
        extends ServiceBaseRepository<Donation> {

}
