package com.lhsystems.module.datageneratorancillary.service.data;

/**
 * The Class BaggageSize.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageSize {

    /** The maximum circumference (w+l+h) of a piece of baggage. */
    private final double circumferenceMax;

    /** The maximum height of a piece of baggage. */
    private final double heightMax;

    /** The maximum length of a piece of baggage. */
    private final double lengthMax;

    /** The maximum width of a piece of baggage. */
    private final double widthMax;

    /**
     * Instantiates a new baggage size.
     *
     * @param paramCircumferenceMax
     *            the maximum circumference
     * @param paramHeightMax
     *            the maximum height
     * @param paramLengthMax
     *            the maximum length
     * @param paramWidthMax
     *            the maximum width
     */
    public BaggageSize(final double paramCircumferenceMax,
            final double paramHeightMax, final double paramLengthMax,
            final double paramWidthMax) {
        circumferenceMax = paramCircumferenceMax;
        heightMax = paramHeightMax;
        lengthMax = paramLengthMax;
        widthMax = paramWidthMax;
    }

    /**
     * returns the circumference max.
     *
     * @return the circumference max
     */
    public double getCircumferenceMax() {
        return circumferenceMax;
    }

    /**
     * returns the height max.
     *
     * @return the height max
     */
    public double getHeightMax() {
        return heightMax;
    }

    /**
     * returns the length max.
     *
     * @return the length max
     */
    public double getLengthMax() {
        return lengthMax;
    }

    /**
     * returns the width max.
     *
     * @return the width max
     */
    public double getWidthMax() {
        return widthMax;
    }

}
