DELETE FROM T_Address; # set unsafe delete in mySql workbench

INSERT INTO T_Address (country, city, street, house, post_code, city_part) VALUES
  ('Ukraine', 'Kiev', 'Amosova st.', '4', '141 00', null),
  ('Czech republic', 'Prague', 'Na Zátorce', '1048/16', '160 00', 'Praha 6 Bubeneč');

SELECT *
FROM T_Address;