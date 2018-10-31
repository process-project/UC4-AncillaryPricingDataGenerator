package com.lhsystems.module.datageneratorancillary.service;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuration, initializes component scan,
 * enable spring jpa repositories and establish connection to database.   .
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Configuration
@ComponentScan
@EnableJpaRepositories("com.lhsystems.module.datageneratorancillary.service.repository")
public class AppConfiguration {

    /**
     * Instantiates a new app configuration. Default constructor to satisfy
     * checkstyle requirements.
     */
    public AppConfiguration() {

    }

    /**
     * Data source bean.
     *
     * @return bean with data source connection
     */
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUsername("sa");
        return dataSource;
        /*
         return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
         */
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
     * Hibernate exception translator.
     *
     * @return the hibernate exception translator
     */
    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    /**
     * Entity manager factory.
     *
     * @return the entity manager factory
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.lhsystems.module.datageneratorancillary.service.data");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
