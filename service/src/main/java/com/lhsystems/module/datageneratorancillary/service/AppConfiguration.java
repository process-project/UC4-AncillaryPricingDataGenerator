/*
package com.lhsystems.module.datageneratorancillary.service;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import static com.lhsystems.module.datageneratorancillary.service.DatabasePropertyName.DRIVER;
import static com.lhsystems.module.datageneratorancillary.service.DatabasePropertyName.GENERATE_DDL;
import static com.lhsystems.module.datageneratorancillary.service.DatabasePropertyName.PASSWORD;
import static com.lhsystems.module.datageneratorancillary.service.DatabasePropertyName.SHOW_SQL;
import static com.lhsystems.module.datageneratorancillary.service.DatabasePropertyName.URL;
import static com.lhsystems.module.datageneratorancillary.service.DatabasePropertyName.USERNAME;

*/
/**
 * Configuration, initializes component scan,
 * enable spring jpa repositories and establish connection to database,
 * specify classpath where database configuration is stored,
 * if classpath is not present, templates properties will be read.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 *//*

@EnableJpaRepositories("com.lhsystems.module.datageneratorancillary.service.repository")
public class AppConfiguration {

    */
/**
     * Environment used for getting database.properties.
     *//*

    private final Environment environment;

    */
/**
     * Instantiates a new app configuration.
     *
     * @param environmentParam
     *       injected spring environment to get database properties
     *//*

    @Autowired
    public AppConfiguration(final Environment environmentParam) {
        this.environment = environmentParam;
    }

    */
/**
     * Data source bean. declares the data source used for reading and writing
     * data.
     *
     * @return bean with data source connection
     *//*

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getEnvironmentProperty(DRIVER));
        dataSource.setUrl(getEnvironmentProperty(URL));
        dataSource.setUsername(getEnvironmentProperty(USERNAME));
        dataSource.setPassword(getEnvironmentProperty(PASSWORD));
        return dataSource;
    }

    */
/**
     * Transaction manager.
     *
     * @return the platform transaction manager
     *//*

    @Bean

    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    */
/**
     * Entity manager factory.
     *
     * @return the entity manager factory
     *//*

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(getBooleanEnvironmentProperty(SHOW_SQL));
        vendorAdapter.setGenerateDdl(getBooleanEnvironmentProperty(GENERATE_DDL));
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.lhsystems.module.datageneratorancillary.service.data");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }


    */
/**
     * Get boolean value from environment property.
     *
     * @param name
     *      database property name
     * @return
     *      boolean value made from property
     *//*

    private boolean getBooleanEnvironmentProperty(final DatabasePropertyName name){
        return Boolean.valueOf(getEnvironmentProperty(name));
    }


    */
/**
     * Get value from environment property.
     *
     * @param propertyName
     *      database property name
     * @return
     *      value from environment property
     *//*

    private String getEnvironmentProperty(final DatabasePropertyName propertyName) {
        return environment.getProperty(propertyName.getPropertyName());
    }
}
*/
