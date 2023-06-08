DROP TABLE IF EXISTS `WeatherCondition`;
DROP TABLE IF EXISTS `SkyCondition`;
DROP TABLE IF EXISTS `Stay`;
DROP TABLE IF EXISTS `Itinerary`;
DROP TABLE IF EXISTS `City`;
DROP TABLE IF EXISTS `User`;


CREATE TABLE IF NOT EXISTS `ItineraryStatus`(
                                                `ID`        INTEGER PRIMARY KEY AUTO_INCREMENT,
                                                `status`    VARCHAR(30) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `SkyCondition`(
                                             `ID`           INTEGER PRIMARY KEY AUTO_INCREMENT,
                                             `condition`    VARCHAR(30) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `City`(
                            `ID`          INTEGER PRIMARY KEY AUTO_INCREMENT,
                            `name`        VARCHAR(1000) NOT NULL UNIQUE,
                            `latitude`    NUMERIC(10, 6) NOT NULL,
                            `longitude`   NUMERIC(10, 6) NOT NULL,
                            `altitude`    NUMERIC(12, 6) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `User`(
                            `ID`        INTEGER PRIMARY KEY AUTO_INCREMENT,
                            `name`      VARCHAR(100) NOT NULL,
                            `surname`   VARCHAR(100) NOT NULL,
                            `email`     VARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS `Itinerary`(
                            `ID`            INTEGER PRIMARY KEY AUTO_INCREMENT,
                            `userID`        INTEGER NOT NULL,
                            `statusID`      VARCHAR(1000) NOT NULL,
                            `description`   VARCHAR(1000) NOT NULL,
                            `startDate`     DATE NOT NULL,
                            `endDate`       DATE NOT NULL,
    FOREIGN KEY (userID) REFERENCES `User` (ID),
    FOREIGN KEY (statusID) REFERENCES `ItineraryStatus` (ID)
    );

CREATE TABLE IF NOT EXISTS `Stay`(
                            `ID`            INTEGER PRIMARY KEY AUTO_INCREMENT,
                            `itineraryID`   INTEGER NOT NULL,
                            `cityID`        INTEGER NOT NULL,
                            `description`   VARCHAR(1000) NOT NULL,
                            `stayDate`      DATE NOT NULL,
    FOREIGN KEY (itineraryID) REFERENCES `Itinerary` (ID),
    FOREIGN KEY (cityID) REFERENCES `City` (ID)
    );

CREATE TABLE IF NOT EXISTS `WeatherCondition`(
                            `ID`               INTEGER PRIMARY KEY AUTO_INCREMENT,
                            `stayID`           INTEGER NOT NULL,
                            `temperature`      NUMERIC(5, 1),
                            `humidity`         NUMERIC(5, 2),
                            `skyConditionID`   INTEGER NOT NULL,
    FOREIGN KEY (stayID) REFERENCES `Stay` (ID),
    FOREIGN KEY (skyConditionID) REFERENCES `SkyCondition` (ID)
    );
