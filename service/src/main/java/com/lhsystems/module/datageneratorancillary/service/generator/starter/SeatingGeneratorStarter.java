package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.SeatGroupConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.SeatGroupGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.SeatGroupRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Starts generating seating entities and save them into database.
 *
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@org.springframework.stereotype.Service
public final class SeatingGeneratorStarter {

    /** The repository used for saving seat groups. */
    private final SeatGroupRepository seatGroupRepository;

    /**
     * Instantiates a new seating generator starter with injected seating
     * repositories.
     *
     * @param seatGroupRepositoryParam
     *            repository responsible for crud operations on seating group
     *            entities
     */
    @Autowired
    public SeatingGeneratorStarter(
            final SeatGroupRepository seatGroupRepositoryParam) {
        seatGroupRepository = seatGroupRepositoryParam;
    }


    /**
     * Generate a list of seat groups as specified in configuration.
     *
     * @param seatGroupConfiguration
     *            configures generation of seatGroups
     * @return the list of generated seat groups
     */
    public List<SeatGroup> generateSeatGroupEntities(
            final SeatGroupConfiguration seatGroupConfiguration) {
        final SeatGroupGenerator seatGroupGenerator = new SeatGroupGenerator(
                seatGroupConfiguration);
        final List<SeatGroup> seatGroups = seatGroupGenerator.generateList(
                seatGroupConfiguration.getNumberSeatGroup());
       // seatGroupRepository.save(seatGroups);
        return seatGroups;
    }

}
