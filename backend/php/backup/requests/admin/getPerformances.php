<?php
/**
 * Created by PhpStorm.
 * User: User
 * Date: 24-Jan-18
 * Time: 12:24 PM
 */
header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';

try {
//    methodAllowed('GET');

    $performances = json_decode(file_get_contents($dataFilePath));

    echo json_encode($performances);
} catch (baseException $e) {
    transformException($e);
}