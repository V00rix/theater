#
#
#
#   SQL DATA QUEERING
#
#
#
#

# Example 1
# Get session data, that occurs on specified time (2018-04-13 19:00:00)
SELECT
  t_session.id AS `session id`,
  t2.date      AS `session date`,
  t_session.hall,
  title        AS `performance title`,
  image_url    AS `image url`,
  description
FROM t_session
  JOIN t_performance
  JOIN t_timestamp t2
WHERE t2.date = "2018-04-13 19:00:00";

# Example 2
# Get layout for each hall:
#   Total number of rows,
#   Number of seats per row,
#   Hidden seats in each row
SELECT
  (SELECT COUNT(*)
   FROM t_hall h INNER JOIN t_row row2 ON h.id = row2.hall
     JOIN t_seat seat ON row2.id = seat.row
   WHERE seat.availabillity = 'HIDDEN') AS `Number of rows`,
  row2.seat_number                      AS `row length`,
  row2.id                               AS `row id`,
  # id is optional here, used for example 4
  row2.number                           AS `row`,
  seat.number                           AS `seat`,
  seat.availabillity
FROM t_hall h INNER JOIN t_row row2 ON h.id = row2.hall
  JOIN t_seat seat ON row2.id = seat.row
WHERE seat.availabillity = 'HIDDEN'
ORDER BY row2.number, seat.number;

# Example 4 (different approach):
# 1. Get rows length
SELECT
  row2.id          AS `row id`,
  row2.number      AS `row number`,
  row2.seat_number AS `seats count`
FROM t_hall h INNER JOIN t_row row2 ON h.id = row2.hall;

# 2. Count returned entries
# 3. For each row get hidden seats (by id)
SELECT
  row2.number AS `row`,
  seat.number AS `seat`,
  seat.availabillity
FROM t_hall h INNER JOIN t_row row2 ON h.id = row2.hall
  JOIN t_seat seat ON row2.id = seat.row
WHERE row2.id = 42 AND seat.availabillity = 'HIDDEN'  # WHERE ... AND row2.id = ${row_id} // php
ORDER BY row2.number, seat.number;

# Example 5
# Get all orders with and their clients + website users
SELECT
  t.name          AS `Registered name`,
  t.email         AS `Registered email`,
  t.login         AS `Registered login`,
  t2.name         AS `Web client name`,
  t2.email        AS `Web client email`,
  t_order.id      AS `Order code`,
  timestamp2.date AS `Order date`
FROM t_order
     FULL OUTER JOIN t_registered_user t ON t_order.registered_email = t.email
JOIN t_website_client t2 ON t_order.website_email = t2.email
JOIN t_timestamp timestamp2 ON t_order.date = timestamp2.id;

# Example 6
# MySQL alternative to ex.5 (where no FULL OUTER JOIN operation is available)
SELECT
  t.name          AS `Registered name`,
  t.email         AS `Registered email`,
  t.login         AS `Registered login`,
  t2.name         AS `Web client name`,
  t2.email        AS `Web client email`,
  t_order.id      AS `Order code`,
  timestamp2.date AS `Order date`
FROM t_order
  LEFT JOIN t_registered_user t ON t_order.registered_email = t.email
  JOIN t_website_client t2 ON t_order.website_email = t2.email
  JOIN t_timestamp timestamp2 ON t_order.date = timestamp2.id
UNION
SELECT
  t.name          AS `Registered name`,
  t.email         AS `Registered email`,
  t.login         AS `Registered login`,
  t2.name         AS `Web client name`,
  t2.email        AS `Web client email`,
  t_order.id      AS `Order code`,
  timestamp2.date AS `Order date`
FROM t_order
  RIGHT JOIN t_registered_user t ON t_order.registered_email = t.email
  JOIN t_website_client t2 ON t_order.website_email = t2.email
  JOIN t_timestamp timestamp2 ON t_order.date = timestamp2.id;

π NAME, email, ORDER /t_order.id (t_order ⟕ t_registered_user ⨝ t_website_client ⨝ t_timestamp)
∪
π NAME, email, ORDER /t_order.id (t_order ⟖ t_registered_user ⨝ t_website_client ⨝ t_timestamp)

# Example 7
# How many viewers have reserved a seat for each date
SELECT
  COUNT(*) AS `viewers`,
  t2.date
FROM t_seat
  JOIN t_order to2 ON t_seat.`order` = to2.id
  JOIN t_session session2 ON t_seat.session = session2.id
  JOIN t_timestamp t2 ON session2.date = t2.id
GROUP BY session2.date;

################################
# Additional inserts for Example 8

#
# SELECT *
# FROM t_performance;
# INSERT INTO t_performance (id, author, title) VALUES (3, 'Second author', 'Second performance title');
#
# SELECT *
# FROM t_timestamp;
# INSERT INTO t_timestamp (id, date) VALUES (13, '2018-04-14 21:00:00');
#
# SELECT *
# FROM t_session;
# INSERT INTO t_session (id, date, hall, performance) VALUES (3, 13, 1, 3);
#
# SELECT *
# FROM t_website_client;
# SELECT *
# FROM t_registered_user;
# INSERT INTO t_registered_user (email, login, name, password) VALUES ('mail@ex.com', 'loginTest', 'nameSC', '123123123');
# INSERT INTO t_website_client (email, name) VALUES ('mail@ex.com', 'nameSC');
# INSERT INTO t_registered_user (email, login, name, password)
# VALUES ('mail2@ex.com', 'loginTest2', 'nameSC222', '123123123222');
# INSERT INTO t_website_client (email, name) VALUES ('mail2@ex.com', 'nameSC222');
# INSERT INTO t_registered_user (email, login, name, password)
# VALUES ('mail3@ex.com', 'loginTest3', 'nameSC333', '1231232331123');
#
# INSERT INTO t_timestamp (id, date) VALUES (312, '2022-05-13 22:05:00');
# INSERT INTO t_timestamp (id, date) VALUES (313, '2022-05-13 22:06:00');
# INSERT INTO t_timestamp (id, date) VALUES (314, '2022-05-13 22:07:00');
# INSERT INTO t_timestamp (id, date) VALUES (315, '2022-05-13 22:08:00');
# INSERT INTO t_timestamp (id, date) VALUES (316, '2022-05-13 22:09:00');
#
# SELECT *
# FROM t_order;
# INSERT INTO t_order (date, registered_email, website_email, checkout, confirmed) VALUES
#   (312, 'mail@ex.com', 'mail@ex.com', 'PAY_BEFORE', TRUE);
# INSERT INTO t_order (date, website_email, checkout, confirmed) VALUES
#   (313, '000 000 000', 'PAY_BEFORE', TRUE);
# INSERT INTO t_order (date, registered_email, website_email, checkout, confirmed) VALUES
#   (314, 'mail2@ex.com', 'mail@ex.com', 'PAY_BEFORE', TRUE);
# INSERT INTO t_order (date, registered_email, checkout, confirmed) VALUES
#   (315, 'mail3@ex.com', 'PAY_BEFORE', TRUE);
# INSERT INTO t_order (date, website_email, checkout, confirmed) VALUES
#   (316, 'mail@ex.com', 'PAY_BEFORE', TRUE);
#
# SELECT *
# FROM t_seat;
# INSERT INTO t_seat (id, number, row, `order`, session, availabillity) VALUES
#   (1, 13, 32, 686, 3, 'BOOKED');
# INSERT INTO t_seat (id, number, row, `order`, session, availabillity) VALUES
#   (2, 14, 32, 687, 3, 'BOOKED');
# INSERT INTO t_seat (id, number, row, `order`, session, availabillity) VALUES
#   (3, 15, 32, 688, 3, 'BOOKED');
# INSERT INTO t_seat (id, number, row, `order`, session, availabillity) VALUES
#   (5, 20, 32, 688, 3, 'BOOKED');
# INSERT INTO t_seat (id, number, row, `order`, session, availabillity) VALUES
#   (4, 16, 32, 689, 3, 'BOOKED');
# INSERT INTO t_seat (id, number, row, `order`, session, availabillity) VALUES
#   (7, 20, 32, 690, 1, 'BOOKED');

# Example 8
# Get all registered users that have visited at least two different performances
SELECT
  email,
  name,
  SUM(ds)  AS `visits`,
  # total number of visits
  COUNT(*) AS `performances` # number of different performances
FROM (SELECT
        title,
        name,
        users.email,
        COUNT(*) AS `ds` # different sessions or/and seats visited by one for performance
      FROM t_order
        # Get performances' data
        JOIN t_seat seat ON t_order.id = seat.`order`
        JOIN t_session session2 ON seat.session = session2.id
        JOIN t_performance tp ON session2.performance = tp.id
        JOIN (SELECT
                # Get first not-null value of email
                COALESCE(rname, wname)   AS name,
                COALESCE(remail, wemail) AS email
              FROM (
                     # Full outer join website clients and registered user by email
                     SELECT
                       w.name  AS `wname`,
                       w.email AS `wemail`,
                       r.name  AS `rname`,
                       r.email AS `remail`
                     FROM t_website_client w
                       LEFT JOIN t_registered_user r ON r.email = w.email
                     UNION
                     SELECT
                       w.name  AS `wname`,
                       w.email AS `wemail`,
                       r.name  AS `remail`,
                       r.email AS `remail`
                     FROM t_website_client w
                       RIGHT JOIN t_registered_user r ON r.email = w.email) AS user) AS users
          ON t_order.website_email = users.email OR t_order.registered_email = users.email
      # Group by performance and email, don't care about seats, etc.
      GROUP BY title, users.email) AS x
GROUP BY email
HAVING COUNT(*) > 1;

USE Theater;

###########################################
# RelAlg
# 1
Selection           σ                 ✓
projection          π                 ✓
Attribute renaming  ρ                 ✓
# 2
NATURAL JOIN        ⨝                 ✓
theta JOIN          Θφ = σ( ER × ES ) ✓
Cartesian product   ×                 ✓
OUTER JOIN          ⟕, ⟖, ⟗
# 3
UNION               ∪                 ✓
Intersection        ∩
-- or --
Minus               \                 ✓
# 4
Division            ÷                 ✓
############################


SELECT * FROM t_order;



# 1. Get performance details for selected session
(
  t_session   (date = "2018-04-13 19:00:00")
              <performance -> id>
              [id, date]
  *
  t_performance
) <image_url -> background>
  [date, author, title, background, description]

# 2. Get performances that AREN'T shown in a theater for each theater
(
  (
    t_performance
    ×
    t_theater
  ) <title -> performance, name -> theater>
    [performance, theater]
  \
  (
    t_performance   <id -> performance_id>
    *
    t_session       <performance -> performance_id, hall -> hall_id>
    *
    t_hall          <id -> hall_id, theater -> theater_id>
    *
    t_theater       <id -> theater_id>
  ) <title -> performance, name -> theater>
    [performance, theater]
)

# or:
(
t_performance   <id -> performance_id>  [title, performance_id]
▷
  (
    t_session       <performance -> performance_id, hall -> hall_id>
    *
    t_hall          <id -> hall_id, theater -> theater_id>
    *
    t_theater       <id -> theater_id>
  ) [performance_id, name]
)     <title -> performance, name -> theater>[performance, theater]


# 3. Get all orders for mail@example.com

R := t_order <registered_email -> email, id -> order_id>  (email = 'mail@example.com')  [order_id, email]
W := t_order <website_email -> email, id -> order_id>     (email = 'mail@example.com')  [order_id, email]

(R ∪ W)

# 4. Get seats that are booked for every session (i.e. most popular seats)
(
  (
    t_seat  <session -> session_id, number -> seat, row -> row_id>
    *
    t_row   <id -> row_id, number -> row>
    [row_id, row]
  )
  ÷
  t_session <id -> session_id>
            [session_id]
) [seat, row, session_id]

# 5. Get orders that were made after performance finished
# (e.g. if insert check was not implemented and we need to erase unnecessary data)
((
  t_order     <date -> timestamp>
  *
  t_timestamp <id -> timestamp>
)<id -> order, date -> d1>[order, d1])
[d1 > d2]
((t_session   <date -> timestamp>
  *
  t_timestamp <id -> timestamp>
)<id -> session, date -> d2>[session, d2])

# 6. Get performances with reviews if possible
t_review
⟖
t_performance