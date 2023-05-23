CREATE TABLE IF NOT EXISTS "user"
(
    id        serial PRIMARY KEY,
    username  VARCHAR(45) NOT NULL,
    password  TEXT        NOT NULL,
    algorithm VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS authority
(
    id     serial PRIMARY KEY,
    name   VARCHAR(45) NOT NULL,
    "user" INT         NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id       serial PRIMARY KEY,
    name     VARCHAR(45) NOT NULL,
    price    VARCHAR(45) NOT NULL,
    currency VARCHAR(45) NOT NULL
);
