package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.Customer;
import com.lhsystems.module.datageneratorancillary.service.data.Gender;
import com.lhsystems.module.datageneratorancillary.service.data.TravelType;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.CustomerConfiguration;

import java.util.HashMap;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.Precision;

/**
 * Generates customers randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class CustomerGenerator extends DataGenerator {

    /** The probability, that a generated customer is a business traveler . */
    private final Double businessProbability;

    /** The probability, that a generated customer is female. */
    private final Double femaleProbability;

    /** The upper limit on the age of a customer. */
    private final int maxCustomerAge;

    /** The upper limit on the satisfaction of a customer. */
    private final int maxSatisfaction;

    /**
     * The mean age used for the distribution sampling the ages of the
     * customers. Due to how the lower and upper limit on the age distort the
     * distribution, this is in general not the mean age of the generated
     * customers
     */
    private final double meanAge;

    /**
     * The mean satisfaction used for the distribution sampling the satisfaction
     * of the customers. Due to how the lower and upper limit on the
     * satisfaction distort the distribution, this is in general not the mean
     * satisfaction of the generated customers
     */
    private final double meanSatisfaction;

    /** The lower limit on the age of a customer. */
    private final int minCustomerAge;

    /** The lower limit on the satisfaction of a customer. */
    private final int minSatisfaction;

    /**
     * The standard deviation of the age used for the distribution sampling the
     * ages of the customers. similar to <code>meanAge</code>, this is in
     * general not the standard deviation of the age of the customers.
     */
    private final double sdAge;

    /**
     * The standard deviation of the satisfaction used for the distribution
     * sampling the satisfaction of the customers. similar to
     * <code>meanSatisfaction</code>, this is in general not the standard
     * deviation of the satisfaction of the customers.
     */
    private final double sdSatisfaction;

    /**
     * Instantiates a new customer generator.
     *
     * @param customerConfiguration
     *            the customer configuration
     */
    public CustomerGenerator(
            final CustomerConfiguration customerConfiguration) {
        meanAge = customerConfiguration.getMeanAge();
        sdAge = customerConfiguration.getSdAge();
        maxCustomerAge = customerConfiguration.getMaxCustomerAge();
        minCustomerAge = customerConfiguration.getMinCustomerAge();
        businessProbability = customerConfiguration.getBusinessProbability();
        femaleProbability = customerConfiguration.getFemaleProbability();
        maxSatisfaction = customerConfiguration.getMaxSatisfaction();
        meanSatisfaction = customerConfiguration.getMeanSatisfaction();
        minSatisfaction = customerConfiguration.getMinSatisfaction();
        sdSatisfaction = customerConfiguration.getSdSatisfaction();
    }

    @Override
    protected Customer generate() {
        final NormalDistribution ageDistribution = new NormalDistribution(
                meanAge,
                sdAge);
        int age = (int) Precision.round(ageDistribution.sample(), 0);
        while ((age < minCustomerAge) || (age > maxCustomerAge)) {
            age = (int) Precision.round(ageDistribution.sample(), 0);
        }
        final HashMap<Gender, Double> genderProbabilities = new HashMap<>();
        genderProbabilities.put(Gender.FEMALE, femaleProbability);
        genderProbabilities.put(Gender.MALE, 1 - femaleProbability);
        final Gender gender = getRandom().getOneRandomElement(
                genderProbabilities);
        final HashMap<TravelType, Double> travelTypeProbabilities = new HashMap<>();
        travelTypeProbabilities.put(TravelType.BUSINESS, businessProbability);
        travelTypeProbabilities.put(
                TravelType.BUSINESS,
                1 - businessProbability);
        final TravelType travelType = getRandom().getOneRandomElement(
                travelTypeProbabilities);
        final NormalDistribution satisfactionDistribution = new NormalDistribution(
                meanSatisfaction,
                sdSatisfaction);
        int satisfaction = (int) Precision.round(
                satisfactionDistribution.sample(),
                0);
        while ((satisfaction < minSatisfaction)
                || (satisfaction > maxSatisfaction)) {
            satisfaction = (int) Precision.round(ageDistribution.sample(), 0);
        }
        return new Customer(age, gender, travelType, satisfaction);
    }
}
