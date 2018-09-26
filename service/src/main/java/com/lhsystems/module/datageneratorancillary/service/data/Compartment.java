package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.*;

/**
 * data structure representing a compartment.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "Compartment")
public final class Compartment {

    /** The id of the compartment. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    /** character identifying the compartment. */
    @Column(name = "IDENTIFIER")
    private final char identifier;

    /** The name of the compartment. */
    @Column(name = "NAME")
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
     * Default Constructor needed for an Entity. Instantiates a new compartment
     * class.
     */
    public Compartment() {
        id = 0;
        identifier = ' ';
        name = null;
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
