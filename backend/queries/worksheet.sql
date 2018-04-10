USE theater;

# Get all performances
SELECT
  id,
  title,
  image_url,
  description
FROM t_performance;

# For each performance get sessions with their related layout (hall)
SELECT
  t_session.id,
  date,
  t_row.hall,
  number,
  seat_number
FROM t_session
  JOIN t_performance tp ON t_session.performance = tp.id
  STRAIGHT_JOIN t_row
ORDER BY date, hall, number;

# For each session get seat statuses (booked, hidden, etc.)
SELECT
  session AS `session id`,
  number,
  row,
  availabillity
FROM t_seat
ORDER BY availabillity DESC, session, row, number;


INSERT INTO t_seat (id, number, row, session, availabillity)
  SELECT
    1101,
    12   AS `seat`,
    r.id AS `row_id`,
    s.id    `session_id`,
    'BOOKED'
  FROM t_row r STRAIGHT_JOIN t_session s
  WHERE r.number = 7 AND s.date = '2018-04-13 19:00:00';
# insert into t_seat
# get row
SELECT
  1,
  2,
  id
FROM t_row
WHERE number = 3;

INSERT INTO t_seat (number, row, session)
  SELECT
    1,
    id,
    3
  FROM t_row
  WHERE number = 4;

SELECT *
FROM t_ticket;
SELECT
  t2.date,
  row2.number,
  t_seat.number,
  t_seat.availabillity
FROM t_seat
  JOIN t_row row2 ON t_seat.row = row2.id
  JOIN t_session session2 ON t_seat.session = session2.id
  JOIN t_timestamp t2 ON session2.date = t2.id;
SELECT *
FROM t_order
  JOIN t_timestamp t2 ON t_order.date = t2.id;

SELECT *
FROM t_timestamp;

SELECT *
FROM t_seat;

SELECT *
FROM t_order;
SELECT *
FROM t_seat
  JOIN t_order to2 ON t_seat.`order` = to2.id
WHERE availabillity = 'BOOKED';

INSERT INTO t_order (date, registered_email, website_email, checkout)
VALUES (146, NULL, 'sadasdasdas', 'SELF_CHECKOUT');

INSERT INTO t_timestamp (date) VALUES (NOW());

INSERT INTO t_order (date, registered_email, website_email, checkout)
VALUES ('143', 'sadasdasdas', NULL, 'SELF_CHECKOUT');

INSERT INTO t_website_client (email, name) VALUES ('sadasdasdas', 'Vsaldas');
SELECT *
FROM t_website_client;
INSERT INTO t_website_client (email, name) VALUES ('hsadsdello', 'wodsadasdasrld');
SELECT email
FROM t_website_client
WHERE email = 'sadasdasdas';

SELECT email
FROM t_registered_user
WHERE email = 'vladogim97@gmail.com';
SELECT email
FROM t_website_client;

SELECT *
FROM t_seat
WHERE availabillity = 'BOOKED';
SELECT *
FROM t_order;

SELECT
  row2.number,
  seat.number
FROM t_ticket
  JOIN t_seat seat ON t_ticket.seat = seat.id
  JOIN t_row row2 ON seat.row = row2.id;

DELETE t_seat.* FROM t_seat JOIN t_order to2 ON t_seat.`order` = to2.id WHERE confirmed = FALSE;

SELECT * FROM t_seat  JOIN t_order to2 ON t_seat.`order` = to2.id
WHERE confirmed = FALSE;

SELECT *
FROM t_order
  JOIN t_website_client t ON t_order.website_email = t.email;

DELETE FROM t_seat WHERE availabillity != 'HIDDEN';
SELECT * FROM t_seat;


SELECT * FROM t_performance;
INSERT INTO t_performance (author, title, image_url, description) VALUES ('выф', 'ывфвыфв', 'test', 'вцвфвыывф');

SELECT
  session,
  t_seat.number,
  row2.number,
  timestamp2.date
FROM t_seat
  JOIN t_ticket t2 ON t_seat.id = t2.seat
  JOIN t_row row2 ON t_seat.row = row2.id
  JOIN t_session session2 ON t_seat.session = session2.id
  JOIN t_timestamp timestamp2 ON session2.date = timestamp2.id;
SELECT *
FROM t_ticket;
SELECT *
FROM t_session;

SELECT
  id,
  checkout,
  t.email,
  t.name
FROM t_order
  JOIN t_website_client t ON t_order.website_email = t.email
  JOIN t_session s ON;
SELECT *
FROM t_seat;

SELECT *
FROM t_order;

SELECT
  `order`,
  t_seat.number,
  row2.number,
  timestamp2.date,
  tp.title
FROM t_seat
  JOIN t_ticket t2 ON t_seat.id = t2.seat
  JOIN t_row row2 ON t_seat.row = row2.id
  JOIN t_session session2 ON t_seat.session = session2.id
  JOIN t_timestamp timestamp2 ON session2.date = timestamp2.id
  JOIN t_performance tp ON session2.performance = tp.id;

SELECT *
FROM t_order;

INSERT INTO t_seat (number, row, session, availabillity, `order`)
  SELECT
    5,
    id,
    2,
    'BOOKED',
    2
  FROM t_row
  WHERE number = 7;

INSERT INTO t_seat (number, row, session, availabillity, `order`)
VALUES (21, 12, 2, 'BOOKED', 7);

SELECT *
FROM t_seat

SELECT
  t_seat.number AS `seat`,
  row2.number   AS `row`,
  timestamp2.date,
  tp.title
FROM t_seat
  JOIN t_row row2 ON t_seat.row = row2.id
  JOIN t_session session2 ON t_seat.session = session2.id
  JOIN t_timestamp timestamp2 ON session2.date = timestamp2.id
  JOIN t_performance tp ON session2.performance = tp.id
WHERE t_seat.`order`

SELECT
  s.id     AS `id`,
  t2.date  AS `date`,
  tp.title AS `title`,
  t2.id    AS `date_id`
FROM t_session s
  JOIN t_timestamp t2 ON s.date = t2.id
  JOIN t_performance tp ON s.performance = tp.id;

SELECT *
FROM t_session;

SELECT *
FROM t_order;
JOIN t_website_client t ON t_order.website_email = t.email;
# where date id  = ...

SELECT *
FROM t_seat
  JOIN t_session session2 ON t_seat.session = session2.id
  JOIN t_timestamp t2 ON session2.date = t2.id
WHERE t_seat.availabillity = 'BOOKED' && t_seat.`order` = 2;

# SELECT t_seat.number as `seat`, row2.number as `row` FROM t_seat
SELECT *
FROM t_seat
  JOIN t_row row2 ON t_seat.row = row2.id;
# where order id = ...

SELECT *
FROM t_session
  JOIN t_timestamp t2 ON t_session.date = t2.id;