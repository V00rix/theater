/* Database creation */
CREATE SCHEMA IF NOT EXISTS theater;
SET search_path TO theater;

/* Drop tables */
-- DROP TABLE IF EXISTS t_review;
-- DROP TABLE IF EXISTS t_friendship;
-- DROP TABLE IF EXISTS t_profile;
DROP TABLE IF EXISTS t_hall CASCADE;
DROP TABLE IF EXISTS t_theater CASCADE;
DROP TABLE IF EXISTS t_client CASCADE;
DROP TABLE IF EXISTS t_seat CASCADE;
DROP TABLE IF EXISTS t_session CASCADE;
DROP TABLE IF EXISTS t_order CASCADE;
DROP TABLE IF EXISTS t_order_seat CASCADE;
-- DROP TABLE IF EXISTS t_registered;
DROP TABLE IF EXISTS t_performance CASCADE;

/* Drop types */
DROP TYPE IF EXISTS checkout CASCADE;
CREATE TYPE checkout AS ENUM ('DELIVERY', 'SELF_CHECKOUT', 'PAY_BEFORE');

CREATE TABLE t_performance (
  id          INT UNIQUE NOT NULL,
  author      VARCHAR(155),
  title       VARCHAR(155),
  image_url   VARCHAR(155),
  description TEXT,
  PRIMARY KEY (author, title)
);

-- CREATE TABLE t_registered (
--   email    VARCHAR(155) PRIMARY KEY,
--   login    VARCHAR(155) NOT NULL UNIQUE,
--   name     VARCHAR(155) NOT NULL,
--   password VARCHAR(155) NOT NULL
-- );

CREATE TABLE t_client (
  id      SERIAL PRIMARY KEY,
  contact VARCHAR(155) NOT NULL UNIQUE,
  name    VARCHAR(155) NOT NULL
);

CREATE TABLE t_theater (
  id            SERIAL UNIQUE,
  name          VARCHAR(155),
  address       VARCHAR(155),
  ticket_price  NUMERIC(10, 3),
  open_time     VARCHAR(155),
  maximum_seats INT NOT NULL,
  PRIMARY KEY (address, name),
  CHECK (maximum_seats > 0)
);

CREATE TABLE t_hall (
  id   SERIAL UNIQUE PRIMARY KEY,
  name VARCHAR UNIQUE NOT NULL,
  seats OID
);
--
-- CREATE TABLE t_seat (
--   y    INT,
--   x    INT,
--   hall INT,
--   foreign key (hall) references t_hall (id)
--   on delete cascade
-- );


CREATE TABLE t_session (
  id          SERIAL UNIQUE,
  hall        INT,
  performance INT,
  date        TIMESTAMP,
  PRIMARY KEY (date, hall, performance),
  FOREIGN KEY (hall) REFERENCES t_hall (id)
  ON DELETE CASCADE,
  FOREIGN KEY (performance) REFERENCES t_performance (id)
  ON DELETE CASCADE
);

CREATE TABLE t_order (
  id         SERIAL UNIQUE,
  created_on TIMESTAMP PRIMARY KEY,
  client     INT      NOT NULL,
  session    INT,
  --   is_digital       BOOL DEFAULT TRUE                                            NOT NULL,
  --   is_purchase      BOOL DEFAULT FALSE                                           NOT NULL,
  checkout   checkout NOT NULL,
  confirmed  BOOL DEFAULT NULL,
  FOREIGN KEY (client) REFERENCES t_client (id) ON DELETE CASCADE,
  FOREIGN KEY (session) REFERENCES t_session (id) ON DELETE SET NULL
);

CREATE TABLE t_order_seat (
  row     INT,
  seat    INT,
  "order" INT,
  foreign key ("order") references t_order (id)
  on delete cascade
);
-- CREATE TABLE t_profile (
--   email     VARCHAR(155) PRIMARY KEY,
--   image_url VARCHAR(155),
--   facebook  VARCHAR(155),
--   google    VARCHAR(155),
--   twitter   VARCHAR(155),
--   FOREIGN KEY (email) REFERENCES t_registered_user (email)
--   ON DELETE CASCADE
-- );

-- CREATE TABLE t_friendship (
--   email_1 VARCHAR(155),
--   email_2 VARCHAR(155),
--   PRIMARY KEY (email_1, email_2),
--   FOREIGN KEY (email_1) REFERENCES t_registered_user (email)
--   ON DELETE CASCADE,
--   FOREIGN KEY (email_2) REFERENCES t_registered_user (email)
--   ON DELETE CASCADE,
--   CHECK (email_1 != email_2)
-- );

-- CREATE TABLE t_review (
--   date        INT,
--   email       VARCHAR(155),
--   performance INT,
--   PRIMARY KEY (date, email, performance),
--   FOREIGN KEY (performance) REFERENCES t_performance (id)
--   ON DELETE CASCADE,
--   FOREIGN KEY (date) REFERENCES t_timestamp (id)
--   ON DELETE CASCADE,
--   FOREIGN KEY (email) REFERENCES t_registered_user (email)
--   ON DELETE CASCADE
-- );
