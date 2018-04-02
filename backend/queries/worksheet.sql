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
FROM t_order JOIN t_timestamp t2 ON t_order.date = t2.id;

SELECT *
FROM t_timestamp;

SELECT * FROM t_order;

INSERT INTO t_order (date, registered_email, website_email, checkout)
VALUES (146, null, 'sadasdasdas', 'SELF_CHECKOUT');

INSERT INTO t_timestamp (date) VALUES (NOW());

INSERT INTO t_order (date, registered_email, website_email, checkout)
VALUES ('143', 'sadasdasdas', null, 'SELF_CHECKOUT');

INSERT INTO t_website_client (email, name) VALUES ('sadasdasdas', 'Vsaldas');
SELECT * FROM t_website_client;
INSERT INTO t_website_client (email, name) VALUES ('hsadsdello', 'wodsadasdasrld');
SELECT email FROM t_website_client WHERE email = 'sadasdasdas';

SELECT email
FROM t_registered_user
WHERE email = 'vladogim97@gmail.com';
SELECT email
FROM t_website_client;

SELECT * FROM t_seat WHERE availabillity = 'BOOKED';
SELECT * FROM t_order;

SELECT row2.number, seat.number FROM t_ticket JOIN t_seat seat ON t_ticket.seat = seat.id JOIN t_row row2 ON seat.row = row2.id;