package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.SeatConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.SeatGroupGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.core.SeatingModelGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.SeatingGroupRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.SeatingModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Starts generating seating entities and save them into database.
 *
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@Service
class SeatingGeneratorStarter {
    /** The repository used for saving seating models. */
    private final SeatingModelRepository seatingModelRepository;

    /** The repository used for saving seating groups. */
    private final SeatingGroupRepository seatingGroupRepository;

    /**
     * Instantiates a new seating generator starer with injected seating repositories.
     *
     * @param seatingModelRepositoryParam
     *        repository responsible for crud operations on seating model entities
     * @param seatingGroupRepositoryParam
     *        repository responsible for crud operations on seating group entities
     */
    @Autowired
    public SeatingGeneratorStarter(final SeatingModelRepository seatingModelRepositoryParam,
                                   final SeatingGroupRepository seatingGroupRepositoryParam) {
        this.seatingModelRepository = seatingModelRepositoryParam;
        this.seatingGroupRepository = seatingGroupRepositoryParam;
    }

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param seatConfiguration
     *        the options used for seat generator
     * @return
     *       the list of generated seat models
     */
    List<SeatingModel> generateSeatingModel(final long startId, final SeatConfiguration seatConfiguration) {
        final List<SeatGroup> seatGroups = generateSeatGroups(startId, seatConfiguration.getSeatGroup());
        return generateSeatingModels(startId, seatGroups, seatConfiguration.getSeatModel());
    }

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param seatGroups
     *        the seat groups from which we chose
     * @param seatModelSize
     *        the size of seat model that should be generated
     * @return
     *        the list of generated seat models
     */
    private List<SeatingModel> generateSeatingModels(final long startId, final List<SeatGroup> seatGroups, final int seatModelSize) {
        final SeatingModelGenerator seatingModelGenerator = new SeatingModelGenerator(startId, seatGroups);
        final List<SeatingModel> seatingModels = seatingModelGenerator.generateList(seatModelSize);
        seatingModelRepository.save(seatingModels);
        return seatingModels;
    }

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param seatGroupSize
     *        the size of seat group that should be generated
     * @return
     *        the list of generated seat groups
     */
    private List<SeatGroup> generateSeatGroups(final long startId, final int seatGroupSize) {
        final SeatGroupGenerator seatGroupGenerator = new SeatGroupGenerator(startId);
        final List<SeatGroup> seatGroups = seatGroupGenerator.generateList(seatGroupSize);
        seatingGroupRepository.save(seatGroups);
        return seatGroups;
    }
}
