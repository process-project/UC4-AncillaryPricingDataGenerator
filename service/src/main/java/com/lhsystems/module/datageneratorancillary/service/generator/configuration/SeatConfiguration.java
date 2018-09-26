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

    /**
     * Gets the seat group size .
     * @return
     *      seat group size
     */
    public int getSeatGroup() {
        return seatGroup;
    }

    /**
     * Set the size of seat group, used for reading yml file.
     *
     * @param seatGroupParam
     *        seatGroup from zml file
     */
    public void setSeatGroup(final int seatGroupParam) {
        this.seatGroup = seatGroupParam;
    }

    /**
     * Gets the seat model size.
     * @return
     *      seat model size
     */
    public int getSeatModel() {
        return seatModel;
    }

    /**
     * Set the size of seat model, used for reading yml file.
     *
     * @param seatModelParam
     *        seatModel from zml file
     */
    public void setSeatModel(final int seatModelParam) {
        this.seatModel = seatModelParam;
    }
}
