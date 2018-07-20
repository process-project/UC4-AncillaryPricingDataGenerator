-- Querys preparing a SQLite database
-- The first query creates a table to store the information of 'Flight' objects.
-- The second query creates a table to store the different markets that are referenced by flight.
-- The third query inserts the currently implemented markets into Market.

CREATE TABLE Flight (
    ID INT NOT NULL,          --'unique identifier of Flight'
    FLIGHT_NO INT,                   --'flight number of the flight'
    DEPARTURE_TIME VARCHAR(10) CHECK(DEPARTURE_TIME LIKE "__:__"),      --'time of day of departure in the Format HH:mm'
    DEPARTURE_DATE VARCHAR(10) CHECK(DEPARTURE_DATE LIKE "____-__-__"),      --'date of departure in the format YYYY-MM-DD'
    ORIGIN_AIRPORT VARCHAR(3),       --'IATA code of the origin airport'
    DESTINATION_AIRPORT VARCHAR(3),  --'IATA code of the destination airport'
    MARKET INT,              --'states if the flight is a domestic, continental, or intercontinental flight'
    PRIMARY KEY(ID),
    FOREIGN KEY(MARKET) REFERENCES Market(ID)
);


CREATE TABLE Market (
    ID INT NOT NULL,
    MARKET VARCHAR(16),
    PRIMARY KEY(ID)
);

CREATE TABLE Airport (
    ID INT NOT NULL,
    IATA VARCHAR(3) CHECK(IATA GLOB [A-Z][A-Z][A-Z]),
    NAME VARCHAR(40),
    MARKET INT,
    PRIMARY KEY(ID),
    FOREIGN KEY(MARKET) REFERENCES MARKET(ID)
);

INSERT INTO Market values(1,"DOMESTIC"), (2,"CONTINENTAL"), (3,"INTERCONTINENTAL");

