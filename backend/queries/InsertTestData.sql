USE Theater;

DELETE FROM t_address;
INSERT INTO t_address (id, country, city, street, house, post_code, city_part) VALUES
  (10, 'Ukraine', 'Kiev', 'Amosova st.', '4', '141 00', NULL),
  (1, 'Czech republic', 'Prague', 'Na Zátorce', '1048/16', '160 00', 'Praha 6 Bubeneč');
SELECT *
FROM t_address;

DELETE FROM t_performance;
INSERT INTO t_performance (id, author, title) VALUES
  (1, 'Great Author', 'My spectacular creation');
SELECT *
FROM t_performance;

DELETE FROM t_timestamp;
INSERT INTO t_timestamp (date) VALUES
  ('2018-04-13 19:00:00'), ('2018-04-13 21:00:00'), ('2018-04-20 19:00:00'),
  ('2018-03-29 10:00:00'), ('2018-03-29 11:00:00');
SELECT *
FROM t_timestamp;

DELETE FROM t_website_client;
INSERT INTO t_website_client (email, name) VALUES
  ('vladogim97@gmail.com', 'Vladik'),
  ('pidorpidor@pidor.pidor', 'Pavel Kuznetsov');
SELECT *
FROM t_website_client;

DELETE FROM t_registered_user;
INSERT INTO t_registered_user (email, login, name, password) SELECT
                                                               email,
                                                               'v00rix',
                                                               name,
                                                               'password'
                                                             FROM t_website_client
                                                             WHERE email = 'vladogim97@gmail.com';
INSERT INTO t_registered_user (email, login, name, password) VALUES
  ('my@mail.com', 'JUST_A_FRIEND', 'hello', 'heslo');
SELECT *
FROM t_registered_user;

DELETE FROM t_phone_client;
INSERT INTO t_phone_client (phone, name) VALUES
  ('123123123', 'TEST_PHONE_CLIENT'),
  ('776558334', 'Vladik');
SELECT *
FROM t_phone_client;

DELETE FROM t_theater;
INSERT INTO t_theater (id, address, name) VALUE
  (1, 1, 'TEST_THEATER'),
  (10, 10, 'WRONG_THEATER');
SELECT *
FROM t_theater;

DELETE FROM t_hall;
INSERT INTO t_hall (id, name, theater) VALUES
  (1, 'TEST_HALL', 1);
SELECT *
FROM t_hall;

DELETE FROM t_chair;
DROP PROCEDURE IF EXISTS createSeats;
DELIMITER #
CREATE PROCEDURE createSeats(p_row INT)
  BEGIN
    DECLARE seats INT UNSIGNED DEFAULT 43;
    DECLARE seat INT UNSIGNED DEFAULT 1;

    START TRANSACTION;
    WHILE seat < seats DO
      INSERT INTO t_chair (row, number, hall) VALUES (p_row, seat, 1);
      SET seat = seat + 1;
    END WHILE;
    COMMIT;
  END #
DELIMITER ;

DROP PROCEDURE IF EXISTS createRows;
DELIMITER #
CREATE PROCEDURE createRows()
  BEGIN
    DECLARE rows INT UNSIGNED DEFAULT 16;
    DECLARE p_row INT UNSIGNED DEFAULT 1;

    START TRANSACTION;
    WHILE p_row < rows DO
      CALL createSeats(p_row);
      SET p_row = p_row + 1;
    END WHILE;
    COMMIT;
  END #
DELIMITER ;

CALL createRows();
# INSERT INTO T_Chair...
SELECT *
FROM t_chair;

DELETE FROM t_session;
INSERT INTO t_session (date, hall_name, hall_theater, performance) SELECT
                                                                     tsp.date,
                                                                     h.name,
                                                                     t.id,
                                                                     p.id
                                                                   FROM theater.t_theater t
                                                                     JOIN t_address a
                                                                       ON t.name = 'TEST_THEATER' AND a.id = t.address
                                                                     JOIN t_hall h ON t.id = h.theater
                                                                     STRAIGHT_JOIN t_timestamp tsp
                                                                     STRAIGHT_JOIN t_performance p
                                                                   ORDER BY tsp.date;
SELECT *
FROM t_session;

DELETE FROM t_seat;
INSERT INTO t_seat (chair, session) SELECT
                                      c.id,
                                      s.id
                                    FROM t_session s
                                      STRAIGHT_JOIN t_chair c
                                    ORDER BY s.id;
SELECT *
FROM t_seat;

SELECT *
FROM t_order;
DELETE FROM t_order;
INSERT INTO t_order (id, date, registered_email, website_email, type, checkout) VALUE
  (1, '2018-03-29 10:00:00', 'vladogim97@gmail.com', 'vladogim97@gmail.com', 'RESERVATION', 'SELF_CHECKOUT');
SELECT *
FROM t_order;

DELETE FROM t_ticket;
INSERT INTO t_ticket (`order`, chair, session) SELECT
                                                 1,
                                                 c.id,
                                                 s.id
                                               FROM t_chair c
                                                 JOIN t_seat seat ON c.id = seat.chair
                                                 JOIN t_session s ON seat.session = s.id
                                               WHERE row = 5 AND number = 14 AND s.date = '2018-04-13 19:00:00';
SELECT *
FROM t_ticket;

DELETE FROM t_profile;
INSERT INTO t_profile (email, photo, facebook) VALUES
  ('vladogim97@gmail.com', LOAD_FILE('C:\\Users\\User\\IdeaProjects\\theater\\backend\\queries\\assets\\test.png'), 'https://www.facebook.com/v.yazykov');
SELECT *
FROM t_profile;

DELETE FROM t_friendship;
INSERT INTO t_friendship (email_1, email_2) VALUES
  ('my@mail.com', 'vladogim97@gmail.com');
SELECT *
FROM t_friendship;

DELETE FROM t_review;
INSERT INTO t_review (date, email, performance) VALUES
  ('2018-03-29 11:00:00', 'vladogim97@gmail.com', 1);
SELECT *
FROM t_review;