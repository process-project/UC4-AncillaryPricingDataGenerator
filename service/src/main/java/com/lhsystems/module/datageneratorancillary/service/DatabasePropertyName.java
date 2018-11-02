package com.lhsystems.module.datageneratorancillary.service;

/**
 * The enum for database property names.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public enum DatabasePropertyName {
    /**
     * Username database property.
     */
    USERNAME("db.username"),
    /**
     * Password database property.
     */
    PASSWORD("db.password"),
    /**
     * Url database property.
     */
    URL("db.url"),
    /**
     * Driver database property.
     */
    DRIVER("db.driver"),
    /**
     * Hibernates' property - show sql.
     */
    SHOW_SQL("hibernate.show.sql"),
    /**
     * Hibernates' property - generate ddl.
     */
    GENERATE_DDL("hibernate.generate.ddl");


    /**
     * The Property name in file.
     */
    private final String propertyName;

    /**
     * Constructor.
     * @param propertyNameValue
     *         propertyName in database.properties
     */
    DatabasePropertyName(final String propertyNameValue) {
        this.propertyName = propertyNameValue;
    }

    /**
     * Gets property name.
     *
     * @return the property name
     */
    public String getPropertyName() {
        return propertyName;
    }
}
