DROP TABLE IF EXISTS `City`;

CREATE TABLE IF NOT EXISTS `City`(
                            `ID`          INTEGER PRIMARY KEY AUTO_INCREMENT,
                            `name`       VARCHAR(1000) NOT NULL UNIQUE,
                            `latitude`      NUMERIC(10, 6) NOT NULL,
                            `longitude`      NUMERIC(10, 6) NOT NULL,
                            `altitude`      NUMERIC(12, 6) NOT NULL
    );