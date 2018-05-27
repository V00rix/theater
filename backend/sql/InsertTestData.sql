SET search_path TO theater;

DELETE FROM t_performance;
INSERT INTO t_performance (id, author, title, image_url, description) VALUES
  (1, 'Great Author', 'My spectacular creation', 'assets/images/performancesBg/test.png', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam consequat odio id libero bibendum, et pharetra elit tincidunt. Suspendisse at nunc sit amet arcu varius sodales. Morbi convallis turpis maximus libero lobortis, ac efficitur orci aliquam. Donec vulputate mattis ante quis posuere. Nullam elementum ex in felis tempus hendrerit. Vivamus eu gravida ipsum. Sed orci diam, tempor eget scelerisque id, venenatis at libero. In ac auctor odio.');
SELECT *
FROM t_performance;

DELETE FROM t_timestamp;
INSERT INTO t_timestamp (id, date) VALUES
  (1, '2018-04-13 19:00:00'), (2, '2018-04-13 21:00:00'), (3, '2018-04-20 19:00:00');
SELECT *
FROM t_timestamp;

DELETE FROM t_website_client;
-- INSERT INTO t_website_client (email, name) VALUES
--   ('vladogim97@gmail.com', 'Vladik'),
--   ('pidorpidor@pidor.pidor', 'Pavel Kuznetsov');
SELECT *
FROM t_website_client;

DELETE FROM t_registered_user;
-- INSERT INTO t_registered_user (email, login, firstName, password) SELECT
--                                                                email,
--                                                                'v00rix',
--                                                                firstName,
--                                                                'password'
--                                                              FROM t_website_client
--                                                              WHERE email = 'vladogim97@gmail.com';
-- INSERT INTO t_registered_user (email, login, firstName, password) VALUES
--   ('my@mail.com', 'JUST_A_FRIEND', 'hello', 'heslo');
SELECT *
FROM t_registered_user;

DELETE FROM t_phone_client;
-- INSERT INTO t_phone_client (phone, firstName) VALUES
--   ('123123123', 'TEST_PHONE_CLIENT'),
--   ('776558334', 'Vlad');
SELECT *
FROM t_phone_client;

DELETE FROM t_theater;
INSERT INTO t_theater (id, country, city, street, house, post_code, city_part, name, maximum_seats) VALUES
  (1, 'Czech republic', 'Prague', 'Na Zatorce', '1048/16', '160 00', 'Praha 6 Bubenec', 'TEST_THEATER', 5);
SELECT *
FROM t_theater;

DELETE FROM t_hall;
INSERT INTO t_hall (id, name, theater) VALUES
  (1, 'TEST_HALL', 1);
SELECT *
FROM t_hall;

DELETE FROM t_row;
INSERT INTO t_row (hall, number, seat_number) VALUES
  (1, 1, 20),
  (1, 2, 17),
  (1, 3, 22),
  (1, 4, 23),
  (1, 5, 24),
  (1, 6, 25),
  (1, 7, 26),
  (1, 8, 27),
  (1, 9, 26),
  (1, 10, 27),
  (1, 11, 31),
  (1, 12, 42),
  (1, 13, 42),
  (1, 14, 42),
  (1, 15, 40);
SELECT *
FROM t_row
ORDER BY hall, number;

DELETE FROM t_session;
INSERT INTO t_session (date, hall, performance) VALUES
  (1, 1, 1),
  (2, 1, 1),
  (3, 1, 1);
SELECT *
FROM t_session;

DELETE FROM t_seat;

INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           4        AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           5        AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           6        AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           7        AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           34       AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           35       AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           36       AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           37       AS "seat",
                                                           r.id     AS row_id,
                                                           s.id     AS "session_id",
                                                           'HIDDEN' AS "availability"
                                                         FROM t_row r
                                                           JOIN t_session s ON 1 = 1
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;
-- INSERT INTO t_seat (id, number,row, session, availabillity)
--   SELECT 1101, 12 as "seat", r.id as "row_id", s.id "session_id", 'BOOKED' FROM t_row r JOIN t_session s WHERE r.number = 7 AND s.date = 1;

SELECT *
FROM t_seat ORDER BY id;

SELECT *
FROM t_order;
DELETE FROM t_order;
-- INSERT INTO t_order (id, date, registered_email, website_email, is_digital, is_purchase, checkout) VALUES
--   (1, 4, 'vladogim97@gmail.com', 'vladogim97@gmail.com', TRUE, TRUE, 'SELF_CHECKOUT');
SELECT *
FROM t_order;

DELETE FROM t_profile;
-- INSERT INTO t_profile (email, image_url, facebook) VALUES
--   ('vladogim97@gmail.com', 'assets/images/profileImages/test.png', 'https://www.facebook.com/v.yazykov');
SELECT *
FROM t_profile;

DELETE FROM t_friendship;
-- INSERT INTO t_friendship (email_1, email_2) VALUES
--   ('my@mail.com', 'vladogim97@gmail.com');
SELECT *
FROM t_friendship;

DELETE FROM t_review;
-- INSERT INTO t_review (date, email, performance) VALUES
--   (5, 'vladogim97@gmail.com', 1);
SELECT *
FROM t_review;