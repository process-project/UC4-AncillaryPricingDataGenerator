package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.utils.ExtendedRandom;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Abstract super class for random data generators.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public abstract class DataGenerator {

    /**
     * Generates a stream of pseudo random numbers used for generating flights.
     */
    private final ExtendedRandom random = new ExtendedRandom();

    /**
     * Instantiates a new data generator.
     *
     */
    public DataGenerator() {
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
    public final <T> List<T> generateList(final long numberToGenerate) {
        return LongStream.range(0, numberToGenerate)
                .mapToObj(n -> (T) generate())
                .collect(Collectors.toList());
    }

    /**
     * Generate an object randomly.
     *
     * @return one object
     */
    protected abstract Object generate();


    /**
     * Returns the random number generator of this object.
     *
     * @return this.random
     */
    protected final ExtendedRandom getRandom() {
        return random;
    }


}
