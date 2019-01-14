package com.lhsystems.module.datageneratorancillary.service;

import org.springframework.context.annotation.*;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySources({
        @PropertySource("classpath:database.properties"),
        @PropertySource(value = "file:${database-properties}", ignoreResourceNotFound=true)
})
@ComponentScan
@EnableCassandraRepositories("com.lhsystems.module.datageneratorancillary.service.cassandra.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    private static final String CASSANDRA_HOST = "cassandra.host";
	private static final String CASSANDRA_PORT = "cassandra.port";

	@Override
    protected String getKeyspaceName() {
        return "bookings";
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints( System.getProperty(CASSANDRA_HOST,"cassandra") );
        cluster.setPort(Integer.parseInt(System.getProperty(CASSANDRA_PORT,"9042")));
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }


}