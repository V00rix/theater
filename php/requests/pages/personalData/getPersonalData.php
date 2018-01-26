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
$seatsFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/seats.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";

/* Get selected personalData */

try {
    methodAllowed('GET'); // todo?????

    session_start();

//    $personalData = $_SESSION['personalData']; // todo ????
    $seats = json_decode(file_get_contents($seatsFilePath));

    echo json_encode($seats);
} catch (baseException $e) {
    transformException($e);
}
