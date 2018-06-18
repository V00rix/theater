<?php
/**
 * Created by PhpStorm.
 * Client: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

//header("Access-Control-Allow-Origin: *");
//header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';
//$seatsFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/personalData.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";

/* Saves selected personalData, title and session time */

try {
    methodAllowed('POST');

    $seats = json_decode(file_get_contents('php://input'));

    session_start();

    // Scene has not been selected yet, but user sends seats -> error
    if (!isset($_SESSION['scene']))
        throw new badArgumentException('$_SESSION[\'SCENE\'] isn\'t set!');

    // Else get selected scene
    $scene = $_SESSION['scene'];

    // object ($seats) comes like this:
    /* seats:
        {
            pData: { row: number, seat: number }[]
            maxRows: number
        }
    */

    // Count scene's number of rows
    $sceneMaxRows = $_SESSION['maxRows'];

    // Check rows and seats
    foreach ($seats->pData as $seat) {
        // Check row boundaries
        if ($seat->row < 0)
            throw new badArgumentException("Selected row ({$seat->row}) is lower than zero");
        if ($seat->row >= $sceneMaxRows)
            throw new badArgumentException("Selected row ({$seat->row}) exceeds scene's number of rows ({$sceneMaxRows} - 1)");

        // Check seat boundaries
        $rowLength = count($scene->seats[$seat->row]);
        if ($seat->seat >= $rowLength)
            throw new badArgumentException("Selected seat ({$seat->seat}) exceeds row's number of seats ({$rowLength})");
        if ($seat->seat < 0)
            throw new badArgumentException("Selected seat ({$seat->seat}) is lower than zero");

        // Check availability
        if ($scene->seats[$seat->row][$seat->seat]->availability != 3)
            throw new badArgumentException("Selected seat ({$seat->row}: {$seat->seat}) isn't available");
    }

    // Validation passed!
    $_SESSION['personalData'] = $seats;

    header("HTTP/1.1 200 OK");
    echo json_encode($_SESSION['personalData']);
} catch (BaseException $e) {
    transformException($e);
}
