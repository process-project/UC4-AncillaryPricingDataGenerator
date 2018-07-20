-- Querys preparing a SQLite database

-- Removing all old tables
DROP TABLE Flight;
DROP TABLE Market;
DROP TABLE Airport;

-- Creates a table to store the information of 'Flight' objects.
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

-- Creates a table to store the different markets that are referenced by flight.
CREATE TABLE Market (
    ID INT NOT NULL, --'unique identifier of Market'
    MARKET VARCHAR(16), --'name of Market'
    PRIMARY KEY(ID)
);

-- Creates a table to store the airports.
CREATE TABLE Airport (
    ID INT NOT NULL, --'unique identifier of Airport'
    IATA VARCHAR(3) CHECK(IATA GLOB '[A-Z][A-Z][A-Z]'), --'3-letter iata Code; only capital letters are allowed '
    NAME VARCHAR(60), --'name of the airport'
    MARKET INT, --'states if the flight is a domestic, continental, or intercontinental flight'
    PRIMARY KEY(ID),
    FOREIGN KEY(MARKET) REFERENCES MARKET(ID)
);

-- Inserts the currently implemented markets into 'Market'.
INSERT INTO Market VALUES(0,"DOMESTIC"), (1,"CONTINENTAL"), (2,"INTERCONTINENTAL");

-- Inserts a number of handpicked airports into 'Airport'.
INSERT INTO Airport VALUES
    ( 0,"JFK","New York J F Kennedy International Apt",2),
    ( 1,"DXB","Dubai International",2),
    ( 2,"KBL","Kabul International",2),
    ( 3,"EZE","Buenos Aires Ministro Pistarini",2),
    ( 4,"BNE","Brisbane International",2),
    ( 5,"GIG","Rio De Janeiro Galeao A C Jobim Int Apt",2),
    ( 6,"YVR","Vancouver International Apt",2),
    ( 7,"SHA","Shanghai Hongqiao International Apt",2),
    ( 8,"HKG","Hong Kong International Apt",2),
    ( 9,"ATL","Atlanta Hartsfield Jackson Intl Apt",2),
    (10,"HND","Tokyo Intl Haneda",2),
    (11,"LHR","London Heathrow Apt",1),
    (12,"CDG","Paris Charles De Gaulle Apt",1),
    (13,"AMS","Amsterdam Schipho",1),
    (14,"IST","Istanbul Ataturk Airport",1),
    (15,"MAD","Madrid Adolfo Suarez Barajas Apt",1),
    (16,"BCN","Barcelona Apt",1),
    (17,"LGW","London Gatwick Apt",1),
    (18,"FCO","Rome Fiumicino Apt",1),
    (19,"SVO","Moscow Sheremetyevo International Apt",1),
    (20,"ORY","Paris Orly Apt",1),
    (21,"FRA","Frankfurt International Apt",0),
    (22,"MUC","Munich International Airport",0),
    (23,"DUS","Duesseldorf International Airport",0),
    (24,"TXL","Berlin Tegel Apt",0),
    (25,"ZRH","Zurich Airport",0),
    (26,"PEK","Beijing Capital Intl Apt",2),
    (27,"LAX","Los Angeles International Apt",2),
    (28,"ORD","Chicago O''Hare International Apt",2),
    (29,"DFW","Dallas Dallas Fort Worth Intl Apt",2),
    (30,"CAN","Guangzhou Baiyun Intl",2),
    (31,"DEL","Delhi Indira Gandhi International",2),
    (32,"CGK","Jakarta Soekarno Hatta Apt",2),
    (33,"ICN","Seoul Incheon International Airport",2),
    (34,"BKK","Bangkok Suvarnabhumi International Apt",2),
    (35,"VIE","Vienna International",0),
    (36,"PVG","Shanghai Pudong International Apt",2),
    (37,"DEN","Denver Intl Apt",2);
