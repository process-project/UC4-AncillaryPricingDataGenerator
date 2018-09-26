package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for seat generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class SeatConfiguration {

    /** Number of seats' models that should be generated. */
    private int seatModel;

    /** Number of seats' groups that should be generated. */
    private int seatGroup;

    public int getSeatGroup() {
        return seatGroup;
    }

    public void setSeatGroup(int seatGroup) {
        this.seatGroup = seatGroup;
    }

    public int getSeatModel() {
        return seatModel;
    }

    public void setSeatModel(int seatModel) {
        this.seatModel = seatModel;
    }
}
