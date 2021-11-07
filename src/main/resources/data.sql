INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE, STATUS)
VALUES ('Admin_First', 'admin@yandex.ru', '$2a$12$MHXLBVEiOFvz2RCjMmBKsON9EjAuusDeAs2luju.k7.U2VVd/c7ki', 'ADMIN', 'ACTIVE'),
       ('Admin_Second', 'second@yandex.ru', '$2a$12$VIG4uXPWFYSYw91htkzWNOkmqY9AAMv9QZd3/4KosDv1A1CLnJ58S', 'ADMIN', 'ACTIVE'),
       ('User_First', 'user@gmail.com', '$2a$12$GeSDXIQielSzom.QAJxm5O3gnXq6akt5XBrzdtlFlUiHHodGfKy/m', 'USER', 'ACTIVE'),
       ('User_Second', 'userSec@gmail.com', '$2a$12$aCa8Edna11paV.s7k8e.QOq5NokPKUuK61NHu5tFDVOxe3sD3zaCW', 'USER', 'BANNED');

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
       (current_timestamp(), '22:35', 1, 1),
       ('2021-03-30', '22:35', 1, 1),
       ('2021-03-30', '22:35', 1, 2),
       ('2020-03-30', '22:35', 1, 3),
       ('2021-03-30', '22:35', 2, 1),
       ('2021-03-30', '22:35', 2, 2),
       ('2021-03-30', '21:31', 2, 3);
