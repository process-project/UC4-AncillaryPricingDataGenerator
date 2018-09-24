package com.lhsystems.module.data.generator.ancillary.generator.starter;

import com.lhsystems.module.data.generator.ancillary.data.SeatGroup;
import com.lhsystems.module.data.generator.ancillary.data.SeatingModel;
import com.lhsystems.module.data.generator.ancillary.generator.configuration.SeatConfiguration;
import com.lhsystems.module.data.generator.ancillary.generator.core.SeatGroupGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.SeatingModelGenerator;
import com.lhsystems.module.data.generator.ancillary.repository.SeatingGroupRepository;
import com.lhsystems.module.data.generator.ancillary.repository.SeatingModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SeatingGeneratorStarter {
    private final SeatingModelRepository seatingModelRepository;
    private final SeatingGroupRepository seatingGroupRepository;

    @Autowired
    public SeatingGeneratorStarter(SeatingModelRepository seatingModelRepository, SeatingGroupRepository seatingGroupRepository) {
        this.seatingModelRepository = seatingModelRepository;
        this.seatingGroupRepository = seatingGroupRepository;
    }

    List<SeatingModel> generateSeatingModel(long startId, SeatConfiguration seatConfiguration) {
        final List<SeatGroup> seatGroups = generateSeatGroups(startId, seatConfiguration.getSeatGroup());
        return generateSeatingModels(startId, seatGroups, seatConfiguration.getSeatModel());
    }

    private List<SeatingModel> generateSeatingModels(long startId, List<SeatGroup> seatGroups, int seatModelSize) {
        SeatingModelGenerator seatingModelGenerator = new SeatingModelGenerator(
                startId, seatGroups);

        List<SeatingModel> seatingModels = seatingModelGenerator.generateList(seatModelSize);
        seatingModelRepository.saveAll(seatingModels);
        return seatingModels;
    }

    private List<SeatGroup> generateSeatGroups(long startId, int seatGroupSize) {
        SeatGroupGenerator seatGroupGenerator = new SeatGroupGenerator(startId);
        final List<SeatGroup> seatGroups = seatGroupGenerator.generateList(seatGroupSize);
        seatingGroupRepository.saveAll(seatGroups);
        return seatGroups;
    }
}
