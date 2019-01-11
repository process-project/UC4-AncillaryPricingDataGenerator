package com.lhsystems.module.datageneratorancillary.service.cassandra.repository;

import com.lhsystems.module.datageneratorancillary.service.cassandra.data.FlightCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCassandraRepository extends CassandraRepository<FlightCassandra> {
}
