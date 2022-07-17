CREATE TABLE users
(
    id          SERIAL      NOT NULL,
    username    VARCHAR(32) NOT NULL,
    email       varchar(32) NOT NULL,
    password    VARCHAR     NOT NULL,
    firstname   VARCHAR(32) ,
    surname     VARCHAR(32) ,
    city        VARCHAR(32) ,
    region      VARCHAR(32) ,
    institution VARCHAR(64) ,
    role_id     INT NOT NULL
        REFERENCES roles (id),
    PRIMARY KEY (id)

);

CREATE TABLE roles
(
    id SERIAL PRIMARY KEY   NOT NULL,
    name        VARCHAR(50) NOT NULL ,
    description VARCHAR(50)

);



CREATE TABLE faculties
(
    id       SERIAL  NOT NULL,
    name         VARCHAR(50) ,
    budget_places    INT     ,
    general_places   INT     ,
    PRIMARY KEY (id)
);

CREATE TABLE subjects
(
    id   SERIAL NOT NULL,
    name_en VARCHAR(50) ,
    name_ru VARCHAR(50) ,
    name_uk VARCHAR(50) ,
    PRIMARY KEY (id)
);