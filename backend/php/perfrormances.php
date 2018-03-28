<?php

print 'Trying to get performances data' . PHP_EOL;


$servername = 'localhost';
$username = 'root';
$password = '';
$dbname = 'theater';

// Create connection
$mysqli = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($mysqli->connect_error) {
  die("Connection failed: " . $mysqli->connect_error);
}
print "Connected successfully" . PHP_EOL;

// queries
if ($result = $mysqli->query("SELECT Name FROM City LIMIT 10")) {
  printf("Select returned %d rows.\n", $result->num_rows);

  /* free result set */
  $result->close();
}
