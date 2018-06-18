<?php
/**
 * Created by PhpStorm.
 * Client: Client
 * Date: 24-Jan-18
 * Time: 12:24 PM
 */
header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/validations/serverMethod.php";

try {
    methodAllowed('GET');

    $performances = json_decode(file_get_contents($dataFilePath));

    $imageUrls = array_map(function ($performance) {
        return $performance->imageUrl;
    }, $performances);

    echo json_encode($imageUrls);
} catch (baseException $e) {
    transformException($e);
}