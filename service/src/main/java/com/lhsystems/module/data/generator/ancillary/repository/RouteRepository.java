package com.lhsystems.module.data.generator.ancillary.repository;

import com.lhsystems.module.data.generator.ancillary.data.Airport;
import com.lhsystems.module.data.generator.ancillary.data.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("Select r from Route r where r.destinationAirport =:destinationIata and r.originAirport =:originIata")
    Route isRouteExists(@Param("originIata") Airport originIata, @Param("destinationIata") Airport destinationIata);

    Route findRouteByDestinationAirportAndOriginAirport(Airport destinationAirport, Airport originAirport);
}
