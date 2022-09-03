DELETE FROM statements
where 1=1;

DELETE FROM users
where id<>1;

INSERT INTO users VALUES (
                             default,'User1','HarryPotter@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Harry','Potter','London','England',
                             'Hogwarts School of Witchcraft and Wizardry',
                             false,1);
INSERT INTO users VALUES (
                             default,'User2','DracoMalfoy@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Draco','Malfoy','London','England',
                             'Hogwarts School of Witchcraft and Wizardry',
                             false,1);
INSERT INTO users VALUES (
                             default,'User3','HermioneGranger@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Hermione','Granger','London','England',
                             'Hogwarts School of Witchcraft and Wizardry',
                             false,1);
INSERT INTO users VALUES (
                             default,'User4','Dobby@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Dobby','Dobby','unknown','Scotland',
                             'Hogwarts School of Witchcraft and Wizardry',
                             false,1);
INSERT INTO users VALUES (
                             default,'User5','NevilleLongbottom@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Neville','Longbottom','London','England',
                             'Hogwarts School of Witchcraft and Wizardry',
                             false,1);
INSERT INTO users VALUES (
                             default,'User6','GinnyWeasley@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Ginny','Weasley','London','England',
                             'Hogwarts School of Witchcraft and Wizardry',
                             false,1);
INSERT INTO users VALUES (
                             default,'User7','RonWeasley@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Ron','Weasley','London','England',
                             'Hogwarts School of Witchcraft and Wizardry',
                             false,1);
INSERT INTO users VALUES (
                             default,'User8','ViktorKrum@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Viktor','Krum','Sofia','Bulgaria',
                             'Durmstrang Institute',
                             false,1);
INSERT INTO users VALUES (
                             default,'User9','FleurDelacour@gmail.com',
                             '$2a$10$4xZK930e50xwPX5Vfcv51eYW7xLqcLKQre3jSGlVt4fxyTzVU9kAS',
                             'Fleur','Delacour','Paris','France',
                             'Beauxbatons Academy of Magic',
                             false,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                                  (SELECT id FROM users WHERE firstname = 'Harry'),118,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                                  (SELECT id FROM users WHERE firstname = 'Harry'),116,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Slytherin'),
                                  (SELECT id FROM users WHERE firstname = 'Harry'),115,1);

INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                                  (SELECT id FROM users WHERE firstname = 'Draco'),128,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                                  (SELECT id FROM users WHERE firstname = 'Draco'),126,1);

INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                                  (SELECT id FROM users WHERE firstname = 'Hermione'),189,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                                  (SELECT id FROM users WHERE firstname = 'Hermione'),190,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Slytherin'),
                                  (SELECT id FROM users WHERE firstname = 'Hermione'),199,1);

INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                                  (SELECT id FROM users WHERE firstname = 'Dobby'),148,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                                  (SELECT id FROM users WHERE firstname = 'Dobby'),146,1);

INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                                  (SELECT id FROM users WHERE firstname = 'Neville'),158,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                                  (SELECT id FROM users WHERE firstname = 'Neville'),156,1);
INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Slytherin'),
                                  (SELECT id FROM users WHERE firstname = 'Neville'),155,1);

INSERT INTO statements values (
                                  default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                                  (SELECT id FROM users WHERE firstname = 'Ginny'),165,1);

INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                              (SELECT id FROM users WHERE firstname = 'Ron'),178,1);
INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                              (SELECT id FROM users WHERE firstname = 'Ron'),176,1);
INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Slytherin'),
                              (SELECT id FROM users WHERE firstname = 'Ron'),175,1);


INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                              (SELECT id FROM users WHERE firstname = 'Viktor'),188,1);
INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                              (SELECT id FROM users WHERE firstname = 'Viktor'),186,1);

INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Gryffindor'),
                              (SELECT id FROM users WHERE firstname = 'Fleur'),198,1);
INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Hufflepuff'),
                              (SELECT id FROM users WHERE firstname = 'Fleur'),196,1);
INSERT INTO statements values (
                     default, (SELECT id FROM faculties WHERE name = 'Slytherin'),
                              (SELECT id FROM users WHERE firstname = 'Fleur'),195,1);

