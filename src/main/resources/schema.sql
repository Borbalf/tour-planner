CREATE TABLE IF NOT EXISTS `City`(
                            `id`          INTEGER PRIMARY KEY,
                            `name`       VARCHAR(1000) NOT NULL UNIQUE
--                             `latitude`      BIGINT NOT NULL,
--                             `longitude`      BIGINT NOT NULL,
--                             `altitude`      BIGINT NOT NULL,
    );