package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.SeatGroupConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.SeatingModelConfiguration;
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

    /** The repository used for saving seat groups. */
    private final SeatGroupRepository seatGroupRepository;

    /**
     * Instantiates a new seating generator starter with injected seating
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
     * Generate seating model.
     *
     * @param seatingModelConfiguration
     *            the seating model configuration
     * @param seatGroupConfiguration
     *            the seat group configuration
     * @return the list of generated seat models
     */
    List<SeatingModel> generateSeatingModel(
            final SeatingModelConfiguration seatingModelConfiguration,
            final SeatGroupConfiguration seatGroupConfiguration) {
        final List<SeatGroup> seatGroups = generateSeatGroups(
                seatGroupConfiguration);
        return generateSeatingModels(
                seatGroups,
                seatingModelConfiguration);
    }

    /**
     * Generate seating models.
     *
     * @param seatGroups
     *        the seat groups from which we chose
     * @param seatingModelConfiguration
     *            the seating model configuration
     * @return the list of generated seat models
     */
    private List<SeatingModel> generateSeatingModels(
            final List<SeatGroup> seatGroups,
            final SeatingModelConfiguration seatingModelConfiguration) {
        final SeatingModelGenerator seatingModelGenerator = new SeatingModelGenerator(
                seatGroups,
                seatingModelConfiguration);
        final List<SeatingModel> seatingModels = seatingModelGenerator.generateList(
                seatingModelConfiguration.getNumberSeatingModel());
        seatingModelRepository.save(seatingModels);
        return seatingModels;
    }

    /**
<<<<<<< Upstream, based on lmuGitlab/topic/EUPLSY-85-2
     * @return
     *        the list of generated seat groups
=======
     * Generate a list of seat groups as specified in configuration.
     *
     * @param seatGroupConfiguration
     *            configures generation of seatGroups
     * @return the list of generated seat groups
>>>>>>> 6401dde EUPLSY-85: Add additional configuration
     */
    private List<SeatGroup> generateSeatGroups(
            final SeatGroupConfiguration seatGroupConfiguration) {
        final SeatGroupGenerator seatGroupGenerator = new SeatGroupGenerator(
                seatGroupConfiguration);
        final List<SeatGroup> seatGroups = seatGroupGenerator.generateList(
                seatGroupConfiguration.getNumberSeatGroup());
        seatGroupRepository.save(seatGroups);
        return seatGroups;
    }
}
