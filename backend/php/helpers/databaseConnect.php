<?php

function db_connect($server_name = 'localhost', $username = 'root', $password = '', $db_name = 'theater')
{
    // Create connection
    $mysqli = new mysqli($server_name, $username, $password, $db_name);

    // Check connection
    if ($mysqli->connect_error) {
        die("Connection failed: " . $mysqli->connect_error);
    }

    return $mysqli;
}
