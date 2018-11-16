package com.lhsystems.module.datageneratorancillary.service.utils;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator;
import org.apache.commons.math3.distribution.AbstractRealDistribution;

/**
 * Realizes a probability distribution based on another distribution by setting
 * the density outside of a interval [<code>min</code>,<code>max</code>] to 0
 * and scaling the density on the inside by a constant factor.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class LimitedRealDistribution extends AbstractRealDistribution {

    /** Serializable version identifier. */
    private static final long serialVersionUID = -8044966810420294203L;

    /** The number of points used when integrating. */
    private static final int INTEGRATOR_POINTS = 3;

    /** The minimal number of iterations done when integrating. */
    private static final double MINIMAL_ITERATION_COUNT = 4;

    /** The maximal number of iterations done when integrating. */
    private static final double MAXIMAL_ITERATION_COUNT = 100;

    /** The maximum number of evaluations done when integrating. */
    private static final int MAXIMUM_NUMBER_EVALUATIONS = 100;

    /** The lower limit on drawn values. */
    private final double min;

    /** The upper limit on drawn values. */
    private final double max;

    /**
     * Some distribution. <code>this</code> is based on
     * <code>distribution</code> by setting the density outside of an interval
     * [min,max] to 0 and scaling the density on the inside by a constant
     * factor.
     */
    private final AbstractRealDistribution distribution;

    /**
     * The factor the density is scaled with, so that it's integral equals 1.
     */
    private final double scalingFactor;

    /**
     * Instantiates a new limited real distribution.
     *
     * @param paramDistribution
     *            the param distribution
     * @param paramMin
     *            the param min
     * @param paramMax
     *            the param max
     */
    public LimitedRealDistribution(
            final AbstractRealDistribution paramDistribution,
            final double paramMin, final double paramMax) {
        if (paramMax <= paramMin) {
            throw new RuntimeException(
                    "The lower limit has to be strictly smaller than the upper limit.");
        }
        distribution = paramDistribution;
        min = paramMin;
        max = paramMax;
        scalingFactor = 1
                / distribution.cumulativeProbability(paramMin, paramMax);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(final double x) {
        return scalingFactor * distribution.probability(x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double density(final double x) {
        return scalingFactor * distribution.density(x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double cumulativeProbability(final double x) {
        if (x <= min) {
            return 0;
        }
        if (x >= max) {
            return 1;
        } else {
            return scalingFactor * distribution.cumulativeProbability(min, x);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getNumericalMean() {
        final LegendreGaussIntegrator integrator = new LegendreGaussIntegrator(
                INTEGRATOR_POINTS,
                MINIMAL_ITERATION_COUNT,
                MAXIMAL_ITERATION_COUNT);
        return integrator.integrate(MAXIMUM_NUMBER_EVALUATIONS, new UnivariateFunction() {

            @Override
            public double value(final double x) {
                return x*density(x);
            }
        },
                min,
                max);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getNumericalVariance() {
        final double mean = getNumericalMean();
        final LegendreGaussIntegrator integrator = new LegendreGaussIntegrator(
                INTEGRATOR_POINTS,
                MINIMAL_ITERATION_COUNT,
                MAXIMAL_ITERATION_COUNT);
        return integrator.integrate(
                MAXIMUM_NUMBER_EVALUATIONS,
                new UnivariateFunction() {

                    @Override
                    public double value(final double x) {
                        return (x - mean) * (x - mean) * density(x);
                    }
                },
                min,
                max);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSupportLowerBound() {
        return min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSupportUpperBound() {
        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSupportConnected() {
        return true;
    }

}
