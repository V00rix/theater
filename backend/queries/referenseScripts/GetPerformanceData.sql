USE theater;

# Get all performances
SELECT
  id,
  title,
  image_url,
  description
FROM t_performance;

# Get sessions
SELECT
  s.id,
  date,
  s.hall
FROM t_session s
  JOIN t_performance tp ON s.performance = tp.id;

# Get layout
SELECT *
FROM t_row;

# For each session get seat statuses (booked, hidden, etc.) (...WHERE t_seat.session = {$session->id})
SELECT
  t_seat.number as `seat`,
  row2.number as `row`,
  availabillity
FROM t_seat
  JOIN t_row row2 ON t_seat.row = row2.id
ORDER BY availabillity DESC, session, row, t_seat.number