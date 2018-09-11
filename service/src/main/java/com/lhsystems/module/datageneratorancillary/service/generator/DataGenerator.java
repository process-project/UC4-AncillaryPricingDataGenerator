package com.lhsystems.module.datageneratorancillary.service.generator;

import com.lhsystems.module.datageneratorancillary.service.ExtendedRandom;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract super class for random data generators.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public abstract class DataGenerator {

    /**
     * Counter to remember which numbers have been used as <code>id</code>
     * already.
     */
    private long idCounter;

    /**
     * Generates a stream of pseudo random numbers used for generating flights.
     */
    private final ExtendedRandom random;

    /**
     * Instantiates a new data generator.
     *
     * @param startId
     *            the smallest id used for data Generation
     * @param paramRandom
     *            the random number generator
     */
    public DataGenerator(final Long startId, final ExtendedRandom paramRandom) {
        setIdCounter(startId);
        random = paramRandom;
    }

    /**
     * Generate a generic list containing a number of objects.
     *
     * @param <T>
     *            the type of the objects
     * @param numberToGenerate
     *            the number of objects to generate
     * @return the list
     */
    public final <T> List<T> generateList(final int numberToGenerate) {
        return Stream.iterate(0, n -> n + 1).limit(numberToGenerate).map(
                n -> (T) generateOne()).collect(Collectors.toList());
    }

    /**
     * Generate one Object.
     *
     * @return the object
     */
    protected final Object generateOne() {
        final long id = increaseIdCounter();
        return generate(id);
    }

    /**
     * Generate an object randomly.
     *
     * @param startId
     *            the id of the generated object
     * @return one object
     */
    protected abstract Object generate(long startId);

    /**
     * Returns the id counter.
     *
     * @return the id counter
     */
    protected final long getIdCounter() {
        return idCounter;
    }

    /**
     * Returns the random number generator of this object.
     *
     * @return this.random
     */
    protected final ExtendedRandom getRandom() {
        return random;
    }

    /**
     * Returns the current value of <code>idCounter</code> and increments it.
     *
     * @return the current value of <code>idCounter</code>
     */
    protected final long increaseIdCounter() {
        final Long tempHelper = idCounter;
        setIdCounter(idCounter + 1);
        return tempHelper;
    }

    /**
     * Sets the current value of <code>idCounter</code> to the given number.
     *
     * @param paramIdCounter
     *            the number that <code>idCounter</code> is set to.
     */
    protected final void setIdCounter(final long paramIdCounter) {
        idCounter = paramIdCounter;
    }

}
