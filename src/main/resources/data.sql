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