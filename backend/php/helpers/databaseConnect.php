<?php

function db_connect($server_name = 'localhost', $username = 'elumixor_user', $password = '2H^yu#1IqS$l', $db_name = 'elumixor_theater')
{
    // Create connection
    $mysqli = new mysqli($server_name, $username, $password, $db_name);

    // Check connection
    if ($mysqli->connect_error) {
        die("Connection failed: " . $mysqli->connect_error);
    }

    return $mysqli;
}
