INSERT INTO City (name, latitude, longitude, altitude) VALUES ('Genova', 44.40478, 8.94439, 19);
INSERT INTO City (name, latitude, longitude, altitude) VALUES ('Savona', 44.30905, 8.47715, 4);
INSERT INTO City (name, latitude, longitude, altitude) VALUES ('Imperia', 43.88917, 8.03933, 10);
INSERT INTO City (name, latitude, longitude, altitude) VALUES ('La Spezia', 44.103000, 9.823750, 3);

INSERT INTO SkyCondition (condition) VALUES ('CLEAR_SKY');
INSERT INTO SkyCondition (condition) VALUES ('CLOUDY');
INSERT INTO SkyCondition (condition) VALUES ('RAIN_SHOWERS');
INSERT INTO SkyCondition (condition) VALUES ('SNOW_SHOWERS');

INSERT INTO ItineraryStatus (status) VALUES ('NOT_READY');
INSERT INTO ItineraryStatus (status) VALUES ('READY');
INSERT INTO ItineraryStatus (status) VALUES ('CONFIRMED');
INSERT INTO ItineraryStatus (status) VALUES ('DONE');

INSERT INTO `User` (name, surname, email) VALUES ('Lucia', 'Verdi', 'lucia@verdi.com');
INSERT INTO `User` (name, surname, email) VALUES ('Paolo', 'Bianchi', 'paolo@bianchi.com');
INSERT INTO `User` (name, surname, email) VALUES ('Mario', 'Rossi', 'mario@rossi.com');

INSERT INTO `Itinerary` (userID, statusID, description, startDate, endDate) VALUES (1, 1, 'itinerario di prova', '2023-06-15', '2023-06-17');
INSERT INTO `Itinerary` (userID, statusID, description, startDate, endDate) VALUES (2, 3, 'Vacanze in Liguria', '2023-06-20', '2023-06-22');

INSERT INTO Stay (itineraryId, cityID, description, stayDate) VALUES (2, 1, 'Prima notte', '2023-06-20');
INSERT INTO Stay (itineraryId, cityID, description, stayDate) VALUES (2, 2, 'Seconda notte', '2023-06-21');
INSERT INTO Stay (itineraryId, cityID, description, stayDate) VALUES (2, 3, 'Terza notte', '2023-06-22');

INSERT INTO WeatherCondition (stayID, temperature, humidity, skyConditionID) VALUES (1, 21.1, 78.17, 2);
INSERT INTO WeatherCondition (stayID, temperature, humidity, skyConditionID) VALUES (2, 22.5, 80.12, 1);
INSERT INTO WeatherCondition (stayID, temperature, humidity, skyConditionID) VALUES (3, 21.8, 79.52, 3);
