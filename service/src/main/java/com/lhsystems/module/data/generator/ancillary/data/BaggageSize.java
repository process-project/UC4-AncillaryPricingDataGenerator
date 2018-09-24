package com.lhsystems.module.data.generator.ancillary.data;

import javax.persistence.*;

/**
 * The Class BaggageSize.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "BaggageSize")
public final class BaggageSize {

    /** The id of the baggage Size. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The maximum circumference (w+l+h) of a piece of baggage. */
    @Column(name = "CIRCUMFERENCE_MAX")
    private final double circumferenceMax;

    /** The maximum height of a piece of baggage. */
    @Column(name = "HEIGHT_MAX")
    private final double heightMax;

    /** The maximum length of a piece of baggage. */
    @Column(name = "LENGTH_MAX")
    private final double lengthMax;


    /** The maximum width of a piece of baggage. */
    @Column(name = "WIDTH_MAX")
    private final double widthMax;

    /**
     * Default Constructor needed for an Entity. Instantiates a new baggage
     * class.
     */
    public BaggageSize() {
        circumferenceMax = 0;
        heightMax = 0;
        lengthMax = 0;
        widthMax = 0;
    }

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
