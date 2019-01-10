package com.lhsystems.module.datageneratorancillary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static com.lhsystems.module.datageneratorancillary.service.DatabasePropertyName.*;

@Configuration
@PropertySources({
        @PropertySource("classpath:database.properties"),
        @PropertySource(value = "file:${database-properties}", ignoreResourceNotFound=true)
})
@ComponentScan
@EnableJpaRepositories("com.lhsystems.module.datageneratorancillary.service.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Autowired
    public CassandraConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected String getKeyspaceName() {
        return "bookings";
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints("127.0.0.1");
        cluster.setPort(9042);
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }

    private final Environment environment;

    /**
     * Data source bean. declares the data source used for reading and writing
     * data.
     *
     * @return bean with data source connection
     */
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getEnvironmentProperty(DRIVER));
        dataSource.setUrl(getEnvironmentProperty(URL));
        dataSource.setUsername(getEnvironmentProperty(USERNAME));
        dataSource.setPassword(getEnvironmentProperty(PASSWORD));
        return dataSource;
    }

    /**
     * Transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    /**
     * Entity manager factory.
     *
     * @return the entity manager factory
     */
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


    /**
     * Get boolean value from environment property.
     *
     * @param name
     *      database property name
     * @return
     *      boolean value made from property
     */
    private boolean getBooleanEnvironmentProperty(final DatabasePropertyName name){
        return Boolean.valueOf(getEnvironmentProperty(name));
    }


    /**
     * Get value from environment property.
     *
     * @param propertyName
     *      database property name
     * @return
     *      value from environment property
     */
    private String getEnvironmentProperty(final DatabasePropertyName propertyName) {
        return environment.getProperty(propertyName.getPropertyName());
    }
}