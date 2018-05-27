/* Database creation */
CREATE SCHEMA IF NOT EXISTS theater;
SET search_path TO theater;

/* Drop tables */
DROP TABLE IF EXISTS t_review;
DROP TABLE IF EXISTS t_friendship;
DROP TABLE IF EXISTS t_profile;
DROP TABLE IF EXISTS t_ticket;
DROP TABLE IF EXISTS t_seat;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_session;
DROP TABLE IF EXISTS t_row;
DROP TABLE IF EXISTS t_hall;
DROP TABLE IF EXISTS t_theater;
DROP TABLE IF EXISTS t_phone_client;
DROP TABLE IF EXISTS t_website_client;
DROP TABLE IF EXISTS t_registered_user;
DROP TABLE IF EXISTS t_timestamp;
DROP TABLE IF EXISTS t_performance;
DROP TABLE IF EXISTS t_address;

/* Drop types */
DROP TYPE IF EXISTS CHECKOUT;
CREATE TYPE CHECKOUT AS ENUM ('DELIVERY', 'SELF_CHECKOUT', 'PAY_BEFORE');
DROP TYPE IF EXISTS AVAILABILITY;
CREATE TYPE AVAILABILITY AS ENUM ('FREE', 'BOOKED', 'HIDDEN');

CREATE TABLE t_performance (
  id          INT UNIQUE NOT NULL,
  author      VARCHAR(155),
  title       VARCHAR(155),
  image_url   VARCHAR(155),
  description TEXT,
  PRIMARY KEY (author, title)
);

CREATE TABLE t_timestamp (
  id   SERIAL UNIQUE,
  date TIMESTAMP PRIMARY KEY
);

CREATE TABLE t_registered_user (
  email    VARCHAR(155) PRIMARY KEY,
  login    VARCHAR(155) NOT NULL UNIQUE,
  name     VARCHAR(155) NOT NULL,
  password VARCHAR(155) NOT NULL
);

CREATE TABLE t_website_client (
  email VARCHAR(155) PRIMARY KEY,
  name  VARCHAR(155) NOT NULL
);

CREATE TABLE t_phone_client (
  phone VARCHAR(155) PRIMARY KEY,
  name  VARCHAR(155) NOT NULL
);

CREATE TABLE t_theater (
  id            SERIAL UNIQUE,
  country       VARCHAR(155),
  city          VARCHAR(155),
  street        VARCHAR(155),
  house         VARCHAR(55),
  post_code     VARCHAR(55),
  city_part     VARCHAR(155),
  name          VARCHAR(155),
  maximum_seats INT                       NOT NULL,
  PRIMARY KEY (country, city, street, house, name),
  CHECK (maximum_seats > 0)
);

CREATE TABLE t_hall (
  id      SERIAL UNIQUE,
  name    VARCHAR(155),
  theater INT,
  FOREIGN KEY (theater) REFERENCES t_theater (id)
    ON DELETE CASCADE,
  PRIMARY KEY (name, theater)
);

CREATE TABLE t_row (
  id          SERIAL UNIQUE,
  hall        INT,
  number      INT,
  seat_number INT                       NOT NULL,
  FOREIGN KEY (hall) REFERENCES t_hall (id)
    ON DELETE CASCADE,
  PRIMARY KEY (hall, number),
  CHECK (seat_number > 0),
  CHECK (number > 0)
);

CREATE TABLE t_session (
  id          SERIAL UNIQUE,
  date        INT,
  hall        INT,
  performance INT,
  PRIMARY KEY (date, hall, performance),
  FOREIGN KEY (hall) REFERENCES t_hall (id)
    ON DELETE CASCADE,
  FOREIGN KEY (performance) REFERENCES t_performance (id)
    ON DELETE CASCADE,
  FOREIGN KEY (date) REFERENCES t_timestamp (id)
    ON DELETE CASCADE
);


CREATE TABLE t_order (
  id               SERIAL UNIQUE,
  date             INT PRIMARY KEY,
  registered_email VARCHAR(155),
  website_email    VARCHAR(155),
  phone_number     VARCHAR(155),
  is_digital       BOOL DEFAULT TRUE                                            NOT NULL,
  is_purchase      BOOL DEFAULT FALSE                                           NOT NULL,
  checkout         CHECKOUT             NOT NULL,
  confirmed        BOOL DEFAULT NULL,
  FOREIGN KEY (date) REFERENCES t_timestamp (id)
    ON DELETE CASCADE,
  FOREIGN KEY (registered_email) REFERENCES t_registered_user (email)
    ON DELETE SET NULL,
  FOREIGN KEY (website_email) REFERENCES t_website_client (email)
    ON DELETE SET NULL,
  FOREIGN KEY (phone_number) REFERENCES t_phone_client (phone)
    ON DELETE SET NULL,
  CHECK (registered_email IS NOT NULL OR website_email IS NOT NULL OR phone_number IS NOT NULL)
);


CREATE TABLE t_seat (
  id            SERIAL UNIQUE,
  number        INT,
  row           INT,
  "order"       INT,  -- seat may be marked as 'HIDDEN', thus not linking to any order
  session       INT,
  availability AVAILABILITY NOT NULL DEFAULT 'FREE',
  PRIMARY KEY (number, row, session),
  FOREIGN KEY (row) REFERENCES t_row (id)
    ON DELETE CASCADE,
  FOREIGN KEY (session) REFERENCES t_session (id)
    ON DELETE CASCADE,
  FOREIGN KEY ("order") REFERENCES t_order (id)
    ON DELETE SET NULL,
  CHECK (number > 0),
  CHECK ("order" IS NOT NULL OR availability = 'HIDDEN')
);

CREATE TABLE t_profile (
  email     VARCHAR(155) PRIMARY KEY,
  image_url VARCHAR(155),
  facebook  VARCHAR(155),
  google    VARCHAR(155),
  twitter   VARCHAR(155),
  FOREIGN KEY (email) REFERENCES t_registered_user (email)
    ON DELETE CASCADE
);

CREATE TABLE t_friendship (
  email_1 VARCHAR(155),
  email_2 VARCHAR(155),
  PRIMARY KEY (email_1, email_2),
  FOREIGN KEY (email_1) REFERENCES t_registered_user (email)
    ON DELETE CASCADE,
  FOREIGN KEY (email_2) REFERENCES t_registered_user (email)
    ON DELETE CASCADE,
  CHECK (email_1 != email_2)
);

CREATE TABLE t_review (
  date        INT,
  email       VARCHAR(155),
  performance INT,
  PRIMARY KEY (date, email, performance),
  FOREIGN KEY (performance) REFERENCES t_performance (id)
    ON DELETE CASCADE,
  FOREIGN KEY (date) REFERENCES t_timestamp (id)
    ON DELETE CASCADE,
  FOREIGN KEY (email) REFERENCES t_registered_user (email)
    ON DELETE CASCADE
);
