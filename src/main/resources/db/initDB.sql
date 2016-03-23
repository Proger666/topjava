DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;
CREATE SEQUENCE meals_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE,
  calories_per_day INTEGER DEFAULT 2000 NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals
(
  meal_id INTEGER PRIMARY KEY DEFAULT nextval('meals_seq'),
  id INTEGER NOT NULL ,
  description TEXT,
  dateTime TIMESTAMP DEFAULT now(),
  calories INTEGER,
  CONSTRAINT meals_idx UNIQUE (meal_id, id),
  FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX datetime_idx ON meals(dateTime);