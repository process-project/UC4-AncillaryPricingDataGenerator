package com.lhsystems.module.datageneratorancillary.service.data;

/**
 * data structure representing a compartment.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class Compartment {

    /** The id of the compartment. */
    private final long id;

    /** character identifying the compartment. */
    private final char identifier;

    /** The name of the compartment. */
    private final String name;

    /**
     * Instantiates a new compartment.
     *
     * @param paramId
     *            the id
     * @param paramIdentifier
     *            the char identifying the compartment
     * @param paramName
     *            the name
     */
    public Compartment(final long paramId, final char paramIdentifier,
            final String paramName) {
        id = paramId;
        identifier = paramIdentifier;
        name = paramName;
    }

    /**
     * returns the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public char getIdentifier() {
        return identifier;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
