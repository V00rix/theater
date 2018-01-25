<?php
/**
 * Created by PhpStorm.
 * User: User
 * Date: 24-Jan-18
 * Time: 11:52 AM
 */
header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/test/dist/app_data/performances.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/test/dist/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/test/dist/php/validations/serverMethod.php";

try {
    methodAllowed('GET');

    session_start();

    if (!isset($_SESSION['appState'])) {
        $_SESSION['appState'] = 1;
    }
    echo(json_encode($_SESSION['appState']));

} catch (baseException $e) {
    transformException($e);
}