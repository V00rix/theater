/* Database creation */
CREATE DATABASE IF NOT EXISTS Theater;
USE Theater;

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

CREATE TABLE t_performance (
  id          INT AUTO_INCREMENT UNIQUE NOT NULL,
  author      VARCHAR(255),
  title       VARCHAR(255),
  image_url   VARCHAR(255),
  description TEXT,
  PRIMARY KEY (author, title)
);

CREATE TABLE t_timestamp (
  id   INT AUTO_INCREMENT UNIQUE NOT NULL,
  date TIMESTAMP PRIMARY KEY
);

CREATE TABLE t_registered_user (
  email    VARCHAR(255) PRIMARY KEY,
  login    VARCHAR(255) NOT NULL UNIQUE,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE t_website_client (
  email VARCHAR(255) PRIMARY KEY,
  name  VARCHAR(255) NOT NULL
);

CREATE TABLE t_phone_client (
  phone VARCHAR(255) PRIMARY KEY,
  name  VARCHAR(255) NOT NULL
);

CREATE TABLE t_theater (
  id            INT AUTO_INCREMENT UNIQUE NOT NULL,
  country       VARCHAR(205),
  city          VARCHAR(205),
  street        VARCHAR(205),
  house         VARCHAR(205),
  post_code     VARCHAR(205),
  city_part     VARCHAR(205),
  name          VARCHAR(255),
  maximum_seats INT                       NOT NULL,
  PRIMARY KEY (country, city, street, house, name),
  CHECK (maximum_seats > 0)
);

CREATE TABLE t_hall (
  id      INT AUTO_INCREMENT UNIQUE NOT NULL,
  name    VARCHAR(255),
  theater INT,
  FOREIGN KEY (theater) REFERENCES t_theater (id)
    ON DELETE CASCADE,
  PRIMARY KEY (name, theater)
);

CREATE TABLE t_row (
  id          INT AUTO_INCREMENT UNIQUE NOT NULL,
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
  id          INT AUTO_INCREMENT UNIQUE NOT NULL,
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
  id               INT  AUTO_INCREMENT UNIQUE                                   NOT NULL,
  date             INT PRIMARY KEY,
  registered_email VARCHAR(255),
  website_email    VARCHAR(255),
  phone_number     VARCHAR(255),
  is_digital       BOOL DEFAULT TRUE                                            NOT NULL,
  is_purchase      BOOL DEFAULT FALSE                                           NOT NULL,
  checkout         ENUM ('DELIVERY', 'SELF_CHECKOUT', 'PAY_BEFORE')             NOT NULL,
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
  id            INT AUTO_INCREMENT UNIQUE         NOT NULL,
  number        INT,
  row           INT,
  `order`       INT,  # seat may be marked as 'HIDDEN', thus not linking to any order
  session       INT,
  availabillity ENUM ('FREE', 'BOOKED', 'HIDDEN') NOT NULL DEFAULT 'FREE',
  PRIMARY KEY (number, row, session),
  FOREIGN KEY (row) REFERENCES t_row (id)
    ON DELETE CASCADE,
  FOREIGN KEY (session) REFERENCES t_session (id)
    ON DELETE CASCADE,
  FOREIGN KEY (`order`) REFERENCES t_order (id)
    ON DELETE CASCADE,
  CHECK (number > 0)
);

CREATE TABLE t_profile (
  email     VARCHAR(255) PRIMARY KEY,
  image_url VARCHAR(255),
  facebook  VARCHAR(255),
  google    VARCHAR(255),
  twitter   VARCHAR(255),
  FOREIGN KEY (email) REFERENCES t_registered_user (email)
    ON DELETE CASCADE
);

CREATE TABLE t_friendship (
  email_1 VARCHAR(255),
  email_2 VARCHAR(255),
  PRIMARY KEY (email_1, email_2),
  FOREIGN KEY (email_1) REFERENCES t_registered_user (email)
    ON DELETE CASCADE,
  FOREIGN KEY (email_2) REFERENCES t_registered_user (email)
    ON DELETE CASCADE,
  CHECK (email_1 != email_2)
);

CREATE TABLE t_review (
  date        INT,
  email       VARCHAR(255),
  performance INT,
  PRIMARY KEY (date, email, performance),
  FOREIGN KEY (performance) REFERENCES t_performance (id)
    ON DELETE CASCADE,
  FOREIGN KEY (date) REFERENCES t_timestamp (id)
    ON DELETE CASCADE,
  FOREIGN KEY (email) REFERENCES t_registered_user (email)
    ON DELETE CASCADE
);
