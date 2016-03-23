DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE meals_seq RESTART WITH 100000;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (id, description, calories, datetime)
    VALUES ('100001', 'Havchik1', '300', '2016-2-13');

INSERT INTO meals (id, description, calories, datetime)
VALUES ('100000','Havchik2', '3300', '2016-2-1');