CREATE TABLE roles
(
    id SERIAL PRIMARY KEY   NOT NULL,
    name        VARCHAR(50) NOT NULL ,
    description VARCHAR(50)

);


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
    PRIMARY KEY (id)
);

CREATE TABLE faculties_subjects
    (
 id SERIAL NOT NULL ,
 faculty_id INT REFERENCES faculties (id) ,
  subject_id INT REFERENCES subjects (id),
    PRIMARY KEY (id)
);


INSERT INTO roles
values (default, 'USER');
INSERT INTO roles
values (default, 'ADMIN');


INSERT INTO subjects
VALUES ( default,'Astronomy','Астрономия');

INSERT INTO subjects
VALUES ( default,
'Defence against the Dark Arts',
'Защита от Тёмных искусств' );

INSERT INTO faculties
VALUES ( default,'Gryffindor',20,30);

INSERT INTO faculties
VALUES (default,'Slytherin',20,30);

INSERT INTO faculties
VALUES (default,'Hufflepuff',20,30);