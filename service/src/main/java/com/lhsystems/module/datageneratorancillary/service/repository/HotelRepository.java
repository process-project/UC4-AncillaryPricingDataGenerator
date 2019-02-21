package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Hotel;

import org.springframework.stereotype.Repository;

/**
 * Spring Data repository used for saving hotel entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Repository
public interface HotelRepository
extends ServiceBaseRepository<Hotel> {

}
