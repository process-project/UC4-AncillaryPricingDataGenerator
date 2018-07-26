package com.lhsystems.module.datageneratorancillary.service;

/**
 * Defines the market in which an airport lies and therefore a flight acts.
 *
 * @author Janek Reichardt
 * @version $Revision: 1.10 $
 */
public enum Market {
    DOMESTIC, CONTINENTAL, INTERCONTINENTAL;

    /**
     * Returns the "bigger" of two <code>markets</code>. Here the following
     * Order is considered DOMESTIC<CONTINENTAL<INTERCONTINENTAL.
     *
     * @param other
     *            the market we compare <code>this</code> to.
     * @return the bigger <code>market</code>
     */
    final public Market getMaximumMarket(final Market other) {
        if (this.compareTo(other) >= 0) {
            return this;
        } else {
            return other;
        }
    }

}
