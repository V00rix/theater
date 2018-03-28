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

/* Saves selected personalData, title and session time */

try {
//    methodAllowed('POST'); todo?????

    $newPerformances = file_get_contents('php://input');

    file_put_contents($dataFilePath, $newPerformances);

    echo $newPerformances;
//    header("HTTP 1.1 200 OK");
} catch (Exception $e) { // )) internal SERVER Error) ))0
    transformException($e);
}
