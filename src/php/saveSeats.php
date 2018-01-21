<?php
/**
 * Created by PhpStorm.
 * User: vlado
 * Date: 21-Jan-18
 * Time: 1:51 PM
 */


header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");

// Post to get saved seats
$params = json_decode(file_get_contents('php://input'));
// TODO: validation
session_start();
$_SESSION['seats'] = $params;
echo(json_encode($_SESSION['seats']));
