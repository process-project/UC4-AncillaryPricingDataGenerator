package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for Customer generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class CustomerConfiguration {

    /** The probability, that a generated customer is a business traveler. */
    private double businessProbability;

    /** The probability, that a generated customer is female. */
    private double femaleProbability;

    /** The upper limit on the age of a customer. */
    private int maxCustomerAge;

    /** The upper limit on the satisfaction of a customer. */
    private int maxSatisfaction;

    /**
     * The mean age used for the distribution sampling the ages of the
     * customers. Due to how the lower and upper limit on the age distort the
     * distribution, this is in general not the mean age of the generated
     * customers.
     */
    private double meanAge;

    /**
     * The mean satisfaction used for the distribution sampling the satisfaction
     * of the customers. Due to how the lower and upper limit on the
     * satisfaction distort the distribution, this is in general not the mean
     * satisfaction of the generated customers.
     */
    private double meanSatisfaction;

    /** The lower limit on the age of a customer. */
    private int minCustomerAge;

    /** The lower limit on the satisfaction of a customer. */
    private int minSatisfaction;

    /** The number of customers to be generated. */
    private int numberCustomers;

    /**
     * The standard deviation of the age used for the distribution sampling the
     * ages of the customers. similar to <code>meanAge</code>, this is in
     * general not the standard deviation of the age of the customers.
     */
    private double sdAge;

    /**
     * The standard deviation of the satisfaction used for the distribution
     * sampling the satisfaction of the customers. similar to
     * <code>meanSatisfaction</code>, this is in general not the standard
     * deviation of the satisfaction of the customers.
     */
    private double sdSatisfaction;

    /**
     * Instantiates a new customer configuration.
     */
    public CustomerConfiguration() {
    }

    /**
     * Gets the business probability.
     *
     * @return the business probability
     */
    public Double getBusinessProbability() {
        return businessProbability;
    }

    /**
     * Gets the female probability.
     *
     * @return the female probability
     */
    public Double getFemaleProbability() {
        return femaleProbability;
    }

    /**
     * Gets the max customer age.
     *
     * @return the max customer age
     */
    public int getMaxCustomerAge() {
        return maxCustomerAge;
    }

    /**
     * Gets the max satisfaction.
     *
     * @return the max satisfaction
     */
    public int getMaxSatisfaction() {
        return maxSatisfaction;
    }

    /**
     * Gets the mean age.
     *
     * @return the mean age
     */
    public double getMeanAge() {
        return meanAge;
    }

    /**
     * Gets the mean satisfaction.
     *
     * @return the mean satisfaction
     */
    public double getMeanSatisfaction() {
        return meanSatisfaction;
    }

    /**
     * Gets the min customer age.
     *
     * @return the min customer age
     */
    public int getMinCustomerAge() {
        return minCustomerAge;
    }

    /**
     * Gets the min satisfaction.
     *
     * @return the min satisfaction
     */
    public int getMinSatisfaction() {
        return minSatisfaction;
    }

    /**
     * Gets the number customers.
     *
     * @return the number customers
     */
    public int getNumberCustomers() {
        return numberCustomers;
    }

    /**
     * Gets the sd age.
     *
     * @return the sd age
     */
    public double getSdAge() {
        return sdAge;
    }

    /**
     * Gets the sd satisfaction.
     *
     * @return the sd satisfaction
     */
    public double getSdSatisfaction() {
        return sdSatisfaction;
    }

    /**
     * Sets the business probability.
     *
     * @param businessProbabilityParam
     *            the new business probability
     */
    public void setBusinessProbability(final Double businessProbabilityParam) {
        businessProbability = businessProbabilityParam;
    }

    /**
     * Sets the female probability.
     *
     * @param femaleProbabilityParam
     *            the new female probability
     */
    public void setFemaleProbability(final Double femaleProbabilityParam) {
        femaleProbability = femaleProbabilityParam;
    }

    /**
     * Sets the max customer age.
     *
     * @param maxCustomerAgeParam
     *            the new max customer age
     */
    public void setMaxCustomerAge(final int maxCustomerAgeParam) {
        maxCustomerAge = maxCustomerAgeParam;
    }

    /**
     * Sets the max satisfaction.
     *
     * @param maxSatisfactionParam
     *            the new max satisfaction
     */
    public void setMaxSatisfaction(final int maxSatisfactionParam) {
        maxSatisfaction = maxSatisfactionParam;
    }

    /**
     * Sets the mean age.
     *
     * @param meanAgeParam
     *            the new mean age
     */
    public void setMeanAge(final double meanAgeParam) {
        meanAge = meanAgeParam;
    }

    /**
     * Sets the mean satisfaction.
     *
     * @param meanSatisfactionParam
     *            the new mean satisfaction
     */
    public void setMeanSatisfaction(final double meanSatisfactionParam) {
        meanSatisfaction = meanSatisfactionParam;
    }

    /**
     * Sets the min customer age.
     *
     * @param minCustomerAgeParam
     *            the new min customer age
     */
    public void setMinCustomerAge(final int minCustomerAgeParam) {
        minCustomerAge = minCustomerAgeParam;
    }

    /**
     * Sets the min satisfaction.
     *
     * @param minSatisfactionParam
     *            the new min satisfaction
     */
    public void setMinSatisfaction(final int minSatisfactionParam) {
        minSatisfaction = minSatisfactionParam;
    }

    /**
     * Sets the number customers.
     *
     * @param paramNumberCustomers
     *            the new number customers
     */
    public void setNumberCustomers(final int paramNumberCustomers) {
        numberCustomers = paramNumberCustomers;
    }

    /**
     * Sets the sd age.
     *
     * @param sdAgeParam
     *            the new sd age
     */
    public void setSdAge(final double sdAgeParam) {
        sdAge = sdAgeParam;
    }

    /**
     * Sets the sd satisfaction.
     *
     * @param sdSatisfactionParam
     *            the new sd satisfaction
     */
    public void setSdSatisfaction(final double sdSatisfactionParam) {
        sdSatisfaction = sdSatisfactionParam;
    }


}
