INSERT INTO USERS (EMAIL, NAME, PASSWORD)
VALUES ('user@gmail.com', 'User_First', 'password'),
       ('user1@gmail.com', 'User_First', 'password1'),
       ('user2@gmail.com', 'User_Second', 'password2'),
       ('user3@gmail.com', 'User_Third', 'password3'),
       ('user4@gmail.com', 'User_Fourth', 'password4'),
       ('user5@gmail.com', 'User_Fifth', 'password5'),
       ('admin@javaops.ru', 'Admin_First', 'admin');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5),
       ('USER', 6),
       ('ADMIN', 7),
       ('USER', 7);

INSERT INTO CAFES (NAME, DESCRIPTION, HOMEPAGE, USER_ID)
VALUES ('cafe1', null, 'cafe1.com', 1),
       ('cafe2', 'new_cafe', 'cafe2.com', 1),
       ('cafe3', 'best_cafe', 'cafe3.com', 2),
       ('cafe4', 'beautiful_cafe', 'cafe4.com', 3),
       ('cafe5', 'old_cafe', 'cafe5.ru', 4),
       ('cafe6', null, 'cafe6.ru', 5),
       ('cafe7', 'bar_cafe', 'cafe7.ru', 5);

INSERT INTO DISHES (DATE_TIME, DISH, PRICE, CAFE_ID)
VALUES ('2021-03-20', 'dish1', 100, 1),
       ('2021-03-20', 'dish2', 150, 1),
       ('2021-03-20', 'dish3', 200, 1),
       ('2021-03-26', 'soup', 100, 1),
       ('2021-03-26', 'meat', 125, 1),
       ('2021-03-26', 'desert', 130, 1),
       ('2021-03-26', 'soup', 50, 2),
       ('2021-03-26', 'meat', 200, 2),
       ('2021-03-26', 'desert', 175, 2)
