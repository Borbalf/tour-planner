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

INSERT INTO `User` (name, surname, email) VALUES ('Mario', 'Rossi', 'mario@rossi.com');