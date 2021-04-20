INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User_First', 'user@gmail.com', 'user'),
       ('Admin_First', 'admin@yandex.ru', 'admin');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 2);

INSERT INTO CAFES (NAME, DESCRIPTION, HOMEPAGE, USER_ID)
VALUES ('cafe1', null, 'cafe1.com', 1),
       ('cafe2', 'new_cafe', 'cafe2.com', 1),
       ('cafe3', 'best_cafe', 'cafe3.com', 2);
--        ('cafe4', 'beautiful_cafe', 'cafe4.com', 3),
--        ('cafe5', 'old_cafe', 'cafe5.ru', 4),
--        ('cafe6', null, 'cafe6.ru', 5),
--        ('cafe7', 'bar_cafe', 'cafe7.ru', 5);

INSERT INTO DISHES (DATE, DISH, PRICE, CAFE_ID)
VALUES ('2021-04-03', 'dish1', 100, 1),
       ('2021-04-03', 'dish2', 150, 1),
       ('2021-04-03', 'dish3', 200, 1),
       ('2021-04-03', 'soup', 100, 1),
       ('2021-04-03', 'meat', 125, 1),
       ('2021-04-02', 'meat', 150, 1),
       ('2021-04-03', 'desert', 130, 1),
       ('2021-04-02', 'soup', 50, 2),
       ('2021-04-02', 'meat', 200, 2),
       ('2021-04-03', 'meat', 200, 2),
       ('2021-04-02', 'desert', 175, 2);

-- INSERT INTO VOICES (DATE, TIME, CAFE_ID, USER_ID)
-- VALUES ('2021-03-30', '22:34', 1, 2),
--        ('2021-03-30', '22:35', 1, ),
--        ('2021-03-30', '22:35', 1, 4),
--        ('2021-01-30', '22:35', 1, 1),
--        ('2021-07-30', '22:35', 1, 1),
--        ('2020-03-30', '22:35', 1, 1),
--        ('2021-03-30', '22:35', 2, 11),
--        ('2021-03-30', '22:35', 2, 1),
--        ('2021-03-30', '21:31', 2, 6);
