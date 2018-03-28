/* Database creation */
CREATE DATABASE IF NOT EXISTS Theater;
USE Theater;

/* Drop tables */
DROP TABLE IF EXISTS T_Review;
DROP TABLE IF EXISTS T_Friendship;
DROP TABLE IF EXISTS T_Profile;
DROP TABLE IF EXISTS T_Ticket;
DROP TABLE IF EXISTS T_Order;
DROP TABLE IF EXISTS T_Seat;
DROP TABLE IF EXISTS T_Session;
DROP TABLE IF EXISTS T_Chair;
DROP TABLE IF EXISTS T_Hall;
DROP TABLE IF EXISTS T_Theater;
DROP TABLE IF EXISTS T_Phone_Client;
DROP TABLE IF EXISTS T_Website_Client;
DROP TABLE IF EXISTS T_Registered_User;
DROP TABLE IF EXISTS T_Timestamp;
DROP TABLE IF EXISTS T_Performance;
DROP TABLE IF EXISTS T_Address;

/* Create Tables */
CREATE TABLE T_Address (
  id        INT AUTO_INCREMENT UNIQUE NOT NULL,
  country   VARCHAR(255) CHARACTER SET utf8
  COLLATE utf8_general_ci             NOT NULL,
  city      VARCHAR(255) CHARACTER SET utf8
  COLLATE utf8_general_ci             NOT NULL,
  street    VARCHAR(255) CHARACTER SET utf8
  COLLATE utf8_general_ci             NOT NULL,
  house     VARCHAR(255)              NOT NULL,
  post_code VARCHAR(255),
  city_part VARCHAR(255) CHARACTER SET utf8
  COLLATE utf8_general_ci,
  PRIMARY KEY (country, city, street, house)
);

CREATE TABLE T_Performance (
  id          INT AUTO_INCREMENT UNIQUE NOT NULL,
  author      VARCHAR(255),
  title       VARCHAR(255) UNIQUE,
  description TEXT,
  actors      VARCHAR(255),
  PRIMARY KEY (author, title)
);

CREATE TABLE T_Timestamp (
  date DATETIME PRIMARY KEY
);

CREATE TABLE T_Registered_User (
  email    VARCHAR(255) PRIMARY KEY,
  login    VARCHAR(255) NOT NULL UNIQUE,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE T_Website_Client (
  email VARCHAR(255) PRIMARY KEY,
  name  VARCHAR(255) NOT NULL
);

CREATE TABLE T_Phone_Client (
  phone VARCHAR(255) PRIMARY KEY,
  name  VARCHAR(255) NOT NULL
);

CREATE TABLE T_Theater (
  id      INT AUTO_INCREMENT UNIQUE NOT NULL,
  address INT                       NOT NULL UNIQUE,
  name    VARCHAR(255)              NOT NULL UNIQUE,
  PRIMARY KEY (address, name),
  FOREIGN KEY (address) REFERENCES T_Address (id) ON DELETE CASCADE
);

CREATE TABLE T_Hall (
  name    VARCHAR(255),
  theater INT,
  FOREIGN KEY (theater) REFERENCES T_Theater (id) ON DELETE CASCADE,
  PRIMARY KEY (name, theater)
);

CREATE TABLE T_Chair (
  id           INT AUTO_INCREMENT UNIQUE NOT NULL,
  row          INT,
  number       INT,
  hall_name    VARCHAR(255),
  hall_theater INT,
  PRIMARY KEY (row, number),
  FOREIGN KEY (hall_name, hall_theater) REFERENCES T_Hall (name, theater) ON DELETE CASCADE
);

CREATE TABLE T_Session (
  id           INT AUTO_INCREMENT UNIQUE NOT NULL,
  date         TIMESTAMP,
  hall_name    VARCHAR(255),
  hall_theater INT,
  performance  INT,
  PRIMARY KEY (date, hall_name, hall_theater, performance),
  FOREIGN KEY (hall_name, hall_theater) REFERENCES T_Hall (name, theater) ON DELETE CASCADE ,
  FOREIGN KEY (performance) REFERENCES T_Performance (id),
  FOREIGN KEY (date) REFERENCES T_Timestamp (date)
);

CREATE TABLE T_Seat (
  chair   INT,
  session INT,
  PRIMARY KEY (chair, session),
  FOREIGN KEY (chair) REFERENCES T_Chair (id) ON DELETE CASCADE ,
  FOREIGN KEY (session) REFERENCES T_Session (id) ON DELETE CASCADE
);

CREATE TABLE T_Order (
  id               INT AUTO_INCREMENT UNIQUE NOT NULL,
  date             TIMESTAMP PRIMARY KEY,
  registered_email VARCHAR(255),
  website_email    VARCHAR(255),
  phone_number     VARCHAR(255),
  type             ENUM ('RESERVATION', 'PURCHASE'),
  checkout         ENUM ('DELIVERY', 'SELF_CHECKOUT', 'PAY_BEFORE'),
  FOREIGN KEY (date) REFERENCES T_Timestamp (date),
  FOREIGN KEY (registered_email) REFERENCES T_Registered_User (email),
  FOREIGN KEY (website_email) REFERENCES T_Website_Client (email),
  FOREIGN KEY (registered_email) REFERENCES T_Phone_Client (phone)
);

CREATE TABLE T_Ticket (
  `order` INT,
  chair   INT,
  session INT,
  PRIMARY KEY (chair, session),
  FOREIGN KEY (chair, session) REFERENCES T_Seat (chair, session) ON DELETE CASCADE,
  FOREIGN KEY (`order`) REFERENCES T_Order (id)
);

CREATE TABLE T_Profile (
  email    VARCHAR(255) PRIMARY KEY,
  photo    BLOB,
  facebook VARCHAR(255),
  google   VARCHAR(255),
  twitter  VARCHAR(255),
  FOREIGN KEY (email) REFERENCES T_Registered_User (email)
);

CREATE TABLE T_Friendship (
  email_1 VARCHAR(255),
  email_2 VARCHAR(255),
  PRIMARY KEY (email_1, email_2),
  FOREIGN KEY (email_1) REFERENCES T_Registered_User (email),
  FOREIGN KEY (email_2) REFERENCES T_Registered_User (email),
  CHECK (email_1 != email_2)
);

CREATE TABLE T_Review (
  date        TIMESTAMP,
  email       VARCHAR(255),
  performance INT,
  PRIMARY KEY (date, email, performance),
  FOREIGN KEY (performance) REFERENCES T_Performance (id)
);
