CREATE KEYSPACE IF NOT EXISTS bookings WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'};

CREATE TABLE IF NOT EXISTS bookings.flight (
    flight_number int,
    id uuid,
    iata_destination_airport text,
    iata_origin_airport text,
    departure_date date,
    departure_time time,
    market text,
    PRIMARY KEY (id, flight_number)
) WITH CLUSTERING ORDER BY (flight_number DESC);

