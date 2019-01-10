package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Spring Data repository used for save route entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
public class RouteRepository  {

    /**
     * Query for checking that is existing route between airports.
     *
     * @param originAirport
     *        origin airport
     * @param destinationAirport
     *        destination airport
     *
     * @return route from database, if not exists returns null
     */
   /* @Query("Select r from Route r where r.destinationAirport =:destinationIata and r.originAirport =:originIata")
    Route isRouteExists(@Param("originIata") Airport originAirport, @Param("destinationIata") Airport destinationAirport);*/

}
