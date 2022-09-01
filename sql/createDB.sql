DROP TABLE IF EXISTS statements;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS faculties_subjects;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS faculties;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;



CREATE TABLE roles
(
    id SERIAL PRIMARY KEY   NOT NULL,
    name        VARCHAR(50) NOT NULL
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
    REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (id)

);

CREATE TABLE subjects
(
    id   SERIAL NOT NULL,
    name_en VARCHAR(50) ,
    name_ru VARCHAR(50) ,
    PRIMARY KEY (id)
);

CREATE TABLE faculties
(
    id       SERIAL  NOT NULL,
    name         VARCHAR(50) ,
    name_ru       varchar(50),
    budget_places    INT     ,
    general_places   INT     ,
    recruitment BOOLEAN NOT NULL
    DEFAULT FALSE,
    PRIMARY KEY (id)
);


CREATE TABLE faculties_subjects
    (
 id SERIAL NOT NULL ,
 faculty_id INT REFERENCES faculties (id) ON DELETE CASCADE ,
  subject_id INT REFERENCES subjects (id) ON DELETE CASCADE,
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
    id SERIAL  NOT NULL ,
    faculty_id INT REFERENCES faculties(id) ON DELETE CASCADE ,
    user_id INT REFERENCES users(id),
    gpa int NOT NULL ,
    position_id INT REFERENCES positions(id) ON DELETE CASCADE,
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
VALUES ( default,
'Defence against the Dark Arts',
'Защита от Тёмных искусств' );

INSERT INTO subjects
VALUES ( default,'Astronomy','Астрономия');

INSERT INTO subjects
VALUES ( default,'Charms','Заклинания');

INSERT INTO subjects
VALUES ( default,'Potions','Зельеварение');

INSERT INTO subjects
VALUES ( default,'History of Magic','История магии');

INSERT INTO subjects
VALUES ( default,'Herbology','Травология');

INSERT INTO subjects
VALUES ( default,'Transfiguration','Трансфигурация');

INSERT INTO faculties
VALUES ( default,'Gryffindor','Гриффиндор',3,5,false);

INSERT INTO faculties
VALUES ( default,'Hufflepuff','Пуффендуй',5,7,false);

INSERT INTO faculties
VALUES ( default,'Slytherin','Cлизерин',1,9,false);


INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Gryffindor'),
        (SELECT id FROM subjects WHERE name_en = 'Charms'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Gryffindor'),
        (SELECT id FROM subjects WHERE name_en = 'Astronomy'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Gryffindor'),
        (SELECT id FROM subjects WHERE name_en = 'History of Magic'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Hufflepuff'),
        (SELECT id FROM subjects WHERE name_en = 'Charms'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Hufflepuff'),
        (SELECT id FROM subjects WHERE name_en = 'Astronomy'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Hufflepuff'),
        (SELECT id FROM subjects WHERE name_en = 'History of Magic'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Slytherin'),
        (SELECT id FROM subjects WHERE name_en = 'Charms'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Slytherin'),
        (SELECT id FROM subjects WHERE name_en = 'Astronomy'));

INSERT INTO faculties_subjects
VALUES (default,(SELECT id FROM faculties WHERE name = 'Slytherin'),
        (SELECT id FROM subjects WHERE name_en = 'History of Magic'));