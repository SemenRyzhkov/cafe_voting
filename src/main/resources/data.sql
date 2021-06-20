INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('Admin_First', 'admin@yandex.ru', 'admin'),
       ('Admin_Second', 'second@yandex.ru', 'second'),
       ('User_First', 'user@gmail.com', 'user'),
       ('User_Second', 'userSec@gmail.com', 'user2');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 1),
       ('USER', 2),
       ('ADMIN', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO CAFES (NAME, DESCRIPTION, HOMEPAGE, USER_ID)
VALUES ('cafe1', null, 'http://cafe1.com', 1),
       ('cafe2', 'new_cafe', 'http://cafe2.com', 1),
       ('cafe3', 'best_cafe', 'http://cafe3.com', 1),
       ('cafe4', 'beautiful_cafe', 'http://cafe4.com', 1),
       ('cafe5', 'old_cafe', 'http://cafe5.ru', 1),
       ('cafe6', null, 'http://cafe6.ru', 1),
       ('cafe7', 'bar_cafe', 'http://cafe7.ru', 1),
       ('cafe8', 'otherCafe', 'http://cafe8.by', 2),
       ('cafe9', 'otherCafe', 'http://cafe9.by', 2);

INSERT INTO DISHES (DATE, DISH, PRICE, CAFE_ID)
VALUES (current_timestamp(), 'dish1', 100, 1),
       (current_timestamp(), 'dish2', 150, 1),
       (current_timestamp(), 'dish3', 200, 1),
       ('2021-04-03', 'soup', 100, 1),
       ('2021-04-03', 'desert', 125, 1),
       ('2021-04-03', 'meat', 150, 1),
       ('2021-04-02', 'desert', 130, 1),
       ('2021-04-02', 'soup', 50, 1),
       ('2021-04-02', 'meat', 200, 1),
       (current_timestamp()-1, 'meat', 200, 2),
       (current_timestamp()-1, 'soup', 200, 2),
       (current_timestamp()-1, 'desert', 175, 2);

INSERT INTO VOICES (DATE, TIME, CAFE_ID, USER_ID)
VALUES (current_timestamp(), '22:34', 1, 4),
       (current_timestamp(), '22:35', 1, 2),
       (current_timestamp(), '22:35', 1, 3),
       ('2021-03-30', '22:35', 1, 1),
       ('2021-03-30', '22:35', 1, 2),
       ('2020-03-30', '22:35', 1, 3),
       ('2021-03-30', '22:35', 2, 1),
       ('2021-03-30', '22:35', 2, 2),
       ('2021-03-30', '21:31', 2, 3);
