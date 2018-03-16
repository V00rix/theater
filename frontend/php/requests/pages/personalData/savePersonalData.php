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
$personalDataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/personalData.json';


require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";

/* Saves selected personalData */

try {
//    methodAllowed('POST'); todo?????

    $personalData = json_decode(file_get_contents('php://input'));

    // TODO...

    session_start();

    /* as
     {
        row: number, seat: number,
        userData?: {
            name?: string,
            phone?: string,
            email?: string,
            vk?: string,
            whatsApp?: string,
            viber?: string,
            telegram?: string
        }
    }[]
     */
    $_SESSION['personalData'] = $personalData;

    file_put_contents($personalDataFilePath, json_encode($personalData));

//    header("HTTP 1.1 200 OK");
    echo json_encode($_SESSION['personalData']);
} catch (Exception $e) {
    transformException($e);
}
