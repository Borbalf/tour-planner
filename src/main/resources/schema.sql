DROP TABLE IF EXISTS `City`;

CREATE TABLE IF NOT EXISTS `City`(
                            `ID`          INTEGER PRIMARY KEY AUTO_INCREMENT,
                            `name`       VARCHAR(1000) NOT NULL UNIQUE
--                             `latitude`      BIGINT NOT NULL,
--                             `longitude`      BIGINT NOT NULL,
--                             `altitude`      BIGINT NOT NULL,
    );