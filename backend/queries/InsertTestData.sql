USE Theater;
DELETE FROM t_address;


INSERT INTO t_address (id, country, city, street, house, post_code, city_part) VALUES
  (10, 'Ukraine', 'Kiev', 'Amosova st.', '4', '141 00', NULL),
  (1, 'Czech republic', 'Prague', 'Na Zátorce', '1048/16', '160 00', 'Praha 6 Bubeneč');


DELETE FROM t_performance;
INSERT INTO t_performance (id, author, title, description, actors) VALUES
  (1, 'Great Author', 'My spectacular creation', NULL, NULL);


DELETE FROM t_timestamp;
INSERT INTO t_timestamp (date) VALUES
  ('2018-04-13 19:00:00'), ('2018-04-13 21:00:00'), ('2018-04-20 19:00:00');


DELETE FROM t_theater;
INSERT INTO t_theater (id, address, name) VALUES
  (1, 1, 'TEST_THEATER'),
  (10, 10, 'WRONG_THEATER');


DELETE FROM t_hall;
INSERT INTO t_hall (id, name, theater) VALUES
  (1, 'TEST_HALL', 1);


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
DELETE FROM t_seat;
INSERT INTO t_seat (chair, session) SELECT
                                      c.id,
                                      s.id
                                    FROM t_session s
                                      STRAIGHT_JOIN t_chair c
                                    ORDER BY s.id;
SELECT * FROM t_seat;

#
# SELECT *
# FROM t_theater
#   JOIN t_address a ON t_theater.address = a.id
#   JOIN t_hall h ON t_theater.id = h.theater;