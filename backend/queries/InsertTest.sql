USE Theater;
DELETE FROM T_Address;

INSERT INTO T_Address (id, country, city, street, house, post_code, city_part) VALUES
  (10, 'Ukraine', 'Kiev', 'Amosova st.', '4', '141 00', null),
  (1, 'Czech republic', 'Prague', 'Na Zátorce', '1048/16', '160 00', 'Praha 6 Bubeneč');


INSERT INTO t_theater (id, address, name) VALUE
  (1, 1, 'TEST_THEATER');

SELECT * FROM t_theater JOIN t_address a ON t_theater.address = a.id;