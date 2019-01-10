package com.lhsystems.module.datageneratorancillary.service.cassandra;

import com.lhsystems.module.datageneratorancillary.service.data.FlightCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCassandraRepository extends CassandraRepository<FlightCassandra> {
}
