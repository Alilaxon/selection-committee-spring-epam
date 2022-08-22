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
    blocked     bool NOT NULL DEFAULT FALSE,
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
    recruitment BOOLEAN NOT NULL
    DEFAULT FALSE,
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

CREATE TABLE positions
(
    id SERIAL NOT NULL ,
    position_name VARCHAR(50),
        PRIMARY KEY (id)
);

    CREATE TABLE statements
(
    id SERIAL NOT NULL ,
    faculty_id INT REFERENCES faculties(id),
    user_id INT REFERENCES users(id),
    gpa int NOT NULL ,
    position_id INT REFERENCES positions(id),
    PRIMARY KEY (id)
    );

INSERT INTO positions
VALUES ( DEFAULT,'REGISTERED');
INSERT INTO positions
VALUES (DEFAULT,'CONTRACT');
INSERT INTO positions
VALUES (DEFAULT,'BUDGET');
INSERT INTO positions
VALUES (DEFAULT,'REJECTED');

INSERT INTO roles
values (default, 'USER');
INSERT INTO roles
values (default, 'ADMIN');

INSERT INTO users VALUES (
default,'admin','admin@gmail.com',
'$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
'Albus','Dumbledore','Mould-on-the-Wold','England',
'Hogwarts School of Witchcraft and Wizardry',
 false,2);


INSERT INTO subjects
VALUES ( default,'Astronomy','Астрономия');

INSERT INTO subjects
VALUES ( default,
'Defence against the Dark Arts',
'Защита от Тёмных искусств' );

