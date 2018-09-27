package com.lhsystems.module.datageneratorancillary.service.utils;

import org.apache.commons.math3.util.Precision;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Adds some functions for generating data randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ExtendedRandom extends Random {

    /**
     * Comment for <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new extended random.
     */
    public ExtendedRandom() {
    }

    /**
     * Instantiates a new extended random with a seed.
     *
     * @param seed
     *            the seed
     */
    public ExtendedRandom(final long seed) {
        super(seed);
    }

    /**
     * Choose multiple elements of a given list randomly.
     *
     * @param <T>
     *            the generic type of the list
     * @param someList
     *            the list
     * @param numberOfElements
     *            the number of elements to be returned
     * @return a list containing random elements of <code>someList</code>
     */
    public <T> List<T> getMultipleRandomElements(final List<T> someList,
            final int numberOfElements) {
        final List<T> chosenElements = new ArrayList<>(
                someList);
        while (chosenElements.size() > numberOfElements) {
            chosenElements.remove(nextInt(chosenElements.size() - 1));
        }
        return chosenElements;
    }

    /**
     * Choose one element of a given list.
     *
     * @param <T>
     *            the generic type of the elements in the list
     * @param someList
     *            the list to chose from
     * @return the chosen element
     */
    public <T> T getOneRandomElement(final List<T> someList) {
        return someList.get(nextInt(someList.size()));
    }

    /**
     * Choose a random number of elements of a given list randomly.
     *
     * @param <T>
     *            the generic type of the list
     * @param someList
     *            the list
     * @param max
     *            the maximum number of elements
     * @param min
     *            the minimum number of elements
     * @return the list
     */
    public <T> List<T> getRandomlyManyElements(final List<T> someList,
            final int min, final int max) {
        final int numberOfElements = Integer.min(
                min + nextInt(max - min),
                someList.size());
        return getMultipleRandomElements(someList, numberOfElements);
    }

    /**
     * Returns a randomly chosen <code>LocalDate</code> in the time interval
     * between <code>minDate</code> and <code>maxDate</code>.
     *
     * @param minDate
     *            the minimum date
     * @param maxDate
     *            the maximum date
     * @return a randomly chosen <code>LocalDate</code> between
     *         <code>minDate</code> and <code>maxDate</code>
     */
    public LocalDate getRandomDay(final LocalDate minDate,
            final LocalDate maxDate) {

        return LocalDate.ofEpochDay(
                minDate.toEpochDay()
                + nextInt(
                        (int) (maxDate.toEpochDay()
                                - minDate.toEpochDay())));

    }

    /**
     * Returns a randomly generated <code>LocalTime</code>.
     *
     * @return a randomly generated <code>LocalTime</code>
     */
    public LocalTime getRandomDaytime() {
        return LocalTime.of(
                nextInt((int) TimeUnit.DAYS.toHours(1)),
                nextInt((int) TimeUnit.HOURS.toMinutes(1)));
    }

    /**
     * Returns a random double between <code>min</code> and <code>max</code>
     * rounded to a certain amount of places.
     *
     * @param min
     *            The minimal value of a double
     *
     * @param max
     *            The maximal value of a double
     *
     * @param decimalPoints
     *            the number of decimal points
     *
     * @return the random double
     */
    public double getRandomRoundedDouble(final double min, final double max,
            final int decimalPoints) {
        return Precision.round(
                min + nextDouble() * (max - min),
                decimalPoints);
    }

    /**
     * Returns a random integer number between <code>min</code> inclusively and
     * <code>max</code> exclusively.
     *
     * @param min
     *            the minimal number
     * @param max
     *            the maximal number (excluded)
     *
     * @return a random integer number
     */
    public int nextInt(final int min, final int max) {
        return nextInt(max - min) + min;
    }
}
