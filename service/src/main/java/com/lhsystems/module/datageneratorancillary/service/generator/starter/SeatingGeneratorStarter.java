package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.SeatConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.SeatGroupGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.core.SeatingModelGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.SeatGroupRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.SeatingModelRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Starts generating seating entities and save them into database.
 *
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
class SeatingGeneratorStarter {
    /** The repository used for saving seating models. */
    private final SeatingModelRepository seatingModelRepository;

    /** The repository used for saving seating groups. */
    private final SeatGroupRepository seatGroupRepository;

    /**
     * Instantiates a new seating generator starer with injected seating
     * repositories.
     *
     * @param seatingModelRepositoryParam
     *            repository responsible for crud operations on seating model
     *            entities
     * @param seatGroupRepositoryParam
     *            repository responsible for crud operations on seating group
     *            entities
     */
    @Autowired
    public SeatingGeneratorStarter(final SeatingModelRepository seatingModelRepositoryParam,
            final SeatGroupRepository seatGroupRepositoryParam) {
        seatingModelRepository = seatingModelRepositoryParam;
        seatGroupRepository = seatGroupRepositoryParam;
    }

    /**
     * @param seatConfiguration
     *        the options used for seat generator
     * @return
     *       the list of generated seat models
     */
    List<SeatingModel> generateSeatingModelEntities(
            final SeatConfiguration seatConfiguration) {
        final List<SeatGroup> seatGroups = generateSeatGroups(
                seatConfiguration.getSeatGroup());
        return generateSeatingModels(
                seatGroups,
                seatConfiguration.getSeatModel());
    }

    /**
     * @param seatGroups
     *        the seat groups from which we chose
     * @param seatModelSize
     *        the size of seat model that should be generated
     * @return
     *        the list of generated seat models
     */
    private List<SeatingModel> generateSeatingModels(
            final List<SeatGroup> seatGroups, final int seatModelSize) {
        final SeatingModelGenerator seatingModelGenerator = new SeatingModelGenerator(
                seatGroups);
        final List<SeatingModel> seatingModels = seatingModelGenerator.generateList(seatModelSize);
        seatingModelRepository.save(seatingModels);
        return seatingModels;
    }

    /**
     * @param seatGroupSize
     *        the size of seat group that should be generated
     * @return
     *        the list of generated seat groups
     */
    private List<SeatGroup> generateSeatGroups(final int seatGroupSize) {
        final SeatGroupGenerator seatGroupGenerator = new SeatGroupGenerator();
        final List<SeatGroup> seatGroups = seatGroupGenerator.generateList(seatGroupSize);
        seatGroupRepository.save(seatGroups);
        return seatGroups;
    }
}
