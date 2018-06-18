<?php
/**
 * Created by PhpStorm.
 * Client: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

//header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';
//$selectedSessionFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/selectedSession.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";

/* Get scene personalData, title and session time */

class Response
{
    public $performanceTitle;
    public $sessionDateTime;
    public $seats;

    public function __construct($performanceTitle, $sessionDateTime, $seats)
    {
        $this->performanceTitle = $performanceTitle;
        $this->sessionDateTime = $sessionDateTime;
        $this->seats = $seats;
    }
}

try {
    methodAllowed('GET');

//    echo $_SERVER['QUERY_STRING'];

    if (!isset($_GET['performanceId']) || !isset($_GET['sessionTime']))
        throw new argumentMissingException("Missing 'performanceId' and/or 'sessionTime'");


    $performance = json_decode(file_get_contents($dataFilePath))[$_GET['performanceId']];

    $scene = null;
    $sessionId = 0;
    foreach ($performance->sessions as $s) {
        if ($s->date == $_GET['sessionTime']) {
            $scene = $s;
            break;
        }
        $sessionId += 1;
    }

    session_start();
    $_SESSION['selectedSessionId'] = $sessionId;
    $_SESSION['scene'] = $scene;

    // TODO: erase
//    $rowIterator = 0;
//    foreach ($scene->seats as $row) {
//        $seatIterator = 0;
////        print_r($rowIterator);
//        foreach ($row as $seat) {
////            print_r($seatIterator);
//            if ($seat->availability != 3) {
//                echo $rowIterator, ' ', $seatIterator, ' A -> ', $seat->availability, "\n";
//            }
//            $seatIterator += 1;
//        }
//        $rowIterator += 1;
//    }

    $_SESSION['maxRows'] = count($scene->seats);

    $response = new Response($performance->title, $scene->date, $scene->seats);

    echo json_encode($response);
} catch (BaseException $e) {
    transformException($e);
}
