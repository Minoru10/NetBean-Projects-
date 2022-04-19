DROP DATABASE IF EXISTS heroSightingDB;
CREATE DATABASE heroSightingDB;

USE heroSightingDB;

CREATE TABLE poweredPersonTbl(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(30) NOT NULL,
    Superpower VARCHAR(50) NOT NULL ,
    details VARCHAR(50),
    Hero Boolean NOT NULL
);

CREATE TABLE locationTbl(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(15) NOT NULL,
    details VARCHAR(50),
    Address VARCHAR(50) NOT NULL, 
    long_lat VARCHAR(30)
);

CREATE TABLE organizationTbl(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(50) NOT NULL,
    phone INT,
    Email VARCHAR(30),
    locationId INT NOT NULL,
    FOREIGN KEY (locationId) REFERENCES locationTbl(id)
);

CREATE TABLE organization_poweredPersonTbl(
    organizationId INT NOT NULL,
    poweredPersonId INT NOT NULL,
    PRIMARY KEY(organizationId, poweredPersonId),
    FOREIGN KEY (organizationId) REFERENCES organizationTbl(id),
    FOREIGN KEY (poweredPersonId) REFERENCES poweredPersonTbl(id)
);

CREATE TABLE sightingsTbl(
    poweredPersonId INT NOT NULL,
    locationId INT NOT NULL,
    Date_Time timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(poweredPersonId, locationId),
    FOREIGN KEY (poweredPersonId) REFERENCES poweredPersonTbl(id),
    FOREIGN KEY (locationId) REFERENCES locationTbl(id)
);