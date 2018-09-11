-- Querys preparing a SQLite database

-- Removing all old tables
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Market;
DROP TABLE IF EXISTS Airport;
DROP TABLE IF EXISTS Compartment;
DROP TABLE IF EXISTS SeatGroup;
DROP TABLE IF EXISTS SeatingModel;
DROP TABLE IF EXISTS BaggageLimits;
DROP TABLE IF EXISTS BaggagePricing;
DROP TABLE IF EXISTS BaggageClass;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Tariff;
DROP TABLE IF EXISTS Product_BaggageClass_Relation;
DROP TABLE IF EXISTS Tariff_SeatingModel_Relation;
DROP TABLE IF EXISTS Flight_Tariff_Relation;
DROP TABLE IF EXISTS SeatingModel_SeatGroup_Relation;


-- Creates a table to store the different markets that are referenced by flight.
CREATE TABLE Market (
    ID INT NOT NULL, --'unique identifier of Market'
    MARKET VARCHAR(16), --'name of Market'
    PRIMARY KEY(ID)
);

-- Creates a table to store the compartments.
CREATE TABLE Compartment (
    ID INT NOT NULL, --'unique identifier of Compartment'
    CHARID VARCHAR CHECK(CHARID GLOB '[A-Z]'),
    NAME VARCHAR(60), --'name of the Compartment'
    PRIMARY KEY(ID)
);

-- Creates a table to store the airports.
CREATE TABLE Airport (
    ID INT NOT NULL, --'unique identifier of Airport'
    IATA VARCHAR(3) CHECK(IATA GLOB '[A-Z][A-Z][A-Z]'), --'3-letter iata Code, only capital letters are allowed '
    NAME VARCHAR(60), --'name of the airport'
    MARKET INT, --'states if the flight is a domestic, continental, or intercontinental flight'
    PRIMARY KEY(ID),
    FOREIGN KEY(MARKET) REFERENCES Market(ID)
);

-- Creates a table to store SeatGroup objects
CREATE TABLE SeatGroup(
    ID INT NOT NULL, --'unique identifier of SeatGroup'
    NAME VARCHAR(60),--'name of the SeatGroup'
    NUMBER_SEATS INT,--'the number of seats in this SeatGroup'
    SEAT_PRICE FLOAT,--'the price of seats in this SeatGroup'
    PRIMARY KEY(ID)
);

-- Creates a table to store BaggageLimits objects
CREATE TABLE BaggageLimits(
    ID INT NOT NULL,        --'unique identifier'
    CIRCUMFERENCE_MAX FLOAT,  --'The maximum circumference (w+l+h) of a piece of baggage'
    COUNT_MAX INT,          --'The maximum count of baggage items'
    HEIGHT_MAX FLOAT,         --'The maximum height of a piece of baggage'
    LENGTH_MAX FLOAT,         --'The maximum length of a piece of baggage'
    WIDTH_MAX FLOAT,          --'The maximum width of a piece of baggage'
    WEIGHT_MAX FLOAT,         --'The maximum weight of a piece of baggage'
    PRIMARY KEY(ID)
);

-- Creates a table to store BaggagePricing objects
CREATE TABLE BaggagePricing(
    ID INT NOT NULL,        --'unique identifier'
    FIRST_PRICE FLOAT,      --'The price of the first bag.'
    SECOND_PRICE FLOAT,     --'The price of the second bag.'
    ADDITIONAL_PRICE FLOAT, --'The price of each additional bag.'
    PRIMARY KEY(ID)
);

-- Creates a table to store BaggageClass objects
CREATE TABLE BaggageClass(
    ID INT NOT NULL,    --'unique identifier'
    NAME VARCHAR(60),   --'name of the BaggageClass'
    BAGGAGE_LIMITS INT, --'assigns a BaggageLimit'
    BAGGAGE_PRICING INT,--'assigns a BaggagePricing'
    PRIMARY KEY(ID),
    FOREIGN KEY(BAGGAGE_LIMITS) REFERENCES BaggageLimits(ID),
    FOREIGN KEY(BAGGAGE_PRICING) REFERENCES BaggagePricing(ID)
);

-- Creates a table to store Product objects
CREATE TABLE Product(
    ID INT NOT NULL,    --'The id of this product'
    NAME VARCHAR(60),   --'The name of the product'
    COMPARTMENT INT,    --'The compartment this product belongs to'
    PRIMARY KEY(ID),
    FOREIGN KEY(COMPARTMENT) REFERENCES Compartment(ID)
);

-- Creates a table to store the relation between Product objects and
-- BaggageClass objects.
CREATE TABLE Product_BaggageClass_Relation(
    ID INT NOT NULL,
    PRODUCT INT,        --'the id of a product'
    BAGGAGE_CLASS INT,      --'the id of a baggage class'
    INCLUDED_BAGS INT,      --'the number of bags included'
    PRIMARY KEY(ID),
    FOREIGN KEY(PRODUCT) REFERENCES Product(ID),
    FOREIGN KEY(BAGGAGE_CLASS) REFERENCES BaggageClass(ID)
);

-- Creates a table to store SeatingModel objects
CREATE TABLE SeatingModel(
    ID INT NOT NULL --'unique identifier'
);

-- Creates a table to store Tariff objects
CREATE TABLE Tariff(
    ID INT NOT NULL, --'the id of a tariff'
    PRICE FLOAT, --'the price of a tariff'
    PRODUCT INT, --'the product a tariff belongs to'
    MARKET INT, --'the market the tariff is offered in'
    SEATING INT,
    PRIMARY KEY(ID),
    FOREIGN KEY(PRODUCT) REFERENCES Product(ID),
    FOREIGN KEY(MARKET) REFERENCES Market(ID),
    FOREIGN KEY(SEATING) REFERENCES SeatingModel(ID)
);

-- Creates a table to store the relation between SeatingModel objects and
-- SeatGroup objects.
CREATE TABLE SeatingModel_SeatGroup_Relation(
    ID INT NOT NULL,
    SEATING_MODEL INT,          --'the id of a seating model'
    SEAT_GROUP INT,             --'the id of a seat group'
    PRIMARY KEY(ID),
    FOREIGN KEY(SEATING_MODEL) REFERENCES SeatingModel(ID),
    FOREIGN KEY(SEAT_GROUP) REFERENCES SeatGroup(ID)
);

-- Creates a table to store Flight objects.
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

-- Creates a table to store the relation between flight objects and
-- Tariff objects.
CREATE TABLE Flight_Tariff_Relation(
    ID INT NOT NULL,
    FLIGHT INT,             --'the id of a flight'
    TARIFF INT,             --'the id of a Tariff'
    PRIMARY KEY(ID),
    FOREIGN KEY(FLIGHT) REFERENCES Flight(ID),
    FOREIGN KEY(TARIFF) REFERENCES Tariff(ID)
);

-- Inserts the currently implemented markets into 'Market'.
INSERT INTO Market VALUES(0,"DOMESTIC"), (1,"CONTINENTAL"), (2,"INTERCONTINENTAL");

-- Inserts two compartments
INSERT INTO Compartment VALUES(0,"M","Economy"), (1,"C","Business");

-- Inserts a number of handpicked airports into 'Airport'.
INSERT INTO Airport VALUES
    ( 0,"JFK","New York J F Kennedy International Airport",2),
    ( 1,"DXB","Dubai International",2),
    ( 2,"KBL","Kabul International",2),
    ( 3,"EZE","Buenos Aires Ministro Pistarini",2),
    ( 4,"BNE","Brisbane International",2),
    ( 5,"GIG","Rio De Janeiro Galeao A C Jobim Int Airport",2),
    ( 6,"YVR","Vancouver International Airport",2),
    ( 7,"SHA","Shanghai Hongqiao International Airport",2),
    ( 8,"HKG","Hong Kong International Airport",2),
    ( 9,"ATL","Atlanta Hartsfield Jackson Intl Airport",2),
    (10,"HND","Tokyo Intl Haneda",2),
    (11,"LHR","London Heathrow Airport",1),
    (12,"CDG","Paris Charles De Gaulle Airport",1),
    (13,"AMS","Amsterdam Schipho",1),
    (14,"IST","Istanbul Ataturk Airport",1),
    (15,"MAD","Madrid Adolfo Suarez Barajas Airport",1),
    (16,"BCN","Barcelona Airport",1),
    (17,"LGW","London Gatwick Airport",1),
    (18,"FCO","Rome Fiumicino Airport",1),
    (19,"SVO","Moscow Sheremetyevo International Airport",1),
    (20,"ORY","Paris Orly Airport",1),
    (21,"FRA","Frankfurt International Airport",0),
    (22,"MUC","Munich International Airport",0),
    (23,"DUS","Duesseldorf International Airport",0),
    (24,"TXL","Berlin Tegel Airport",0),
    (25,"ZRH","Zurich Airport",0),
    (26,"PEK","Beijing Capital Intl Airport",2),
    (27,"LAX","Los Angeles International Airport",2),
    (28,"ORD","Chicago O''Hare International Airport",2),
    (29,"DFW","Dallas Dallas Fort Worth International Airport",2),
    (30,"CAN","Guangzhou Baiyun International Airport",2),
    (31,"DEL","Delhi Indira Gandhi International Airport",2),
    (32,"CGK","Jakarta Soekarno Hatta Airport",2),
    (33,"ICN","Seoul Incheon International Airport",2),
    (34,"BKK","Bangkok Suvarnabhumi International Airport",2),
    (35,"VIE","Vienna International Airport",0),
    (36,"PVG","Shanghai Pudong International Airport",2),
    (37,"DEN","Denver International Airport",2);

