CREATE KEYSPACE IF NOT EXISTS bookings WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;


CREATE TABLE IF NOT EXISTS bookings.flights (
    flight_number int,
    id uuid,
    iata_destination_airport text,
    iata_origin_airport text,
    market text,
    PRIMARY KEY (id, flight_number)
) WITH CLUSTERING ORDER BY (flight_number DESC);

