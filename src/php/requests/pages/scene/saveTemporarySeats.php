<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/src/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/src/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/src/php/models/Exceptions.php";

/* Saves selected seats, title and session time */

try {
//    methodAllowed('POST'); todo?????

    $seats = json_decode(file_get_contents('php://input'));

    // TODO...

    session_start();

    $_SESSION['seats'] = $seats;

    echo json_encode($_SESSION['seats']);
    header("HTTP 1.1 200 OK");
} catch (baseException $e) {
    transformException($e);
}
