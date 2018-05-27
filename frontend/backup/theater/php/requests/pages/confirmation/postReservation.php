<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

//header("Access-Control-Allow-Origin: *");
//header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';
//$personalDataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/personalData.json';
//$selectedPerformanceFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/selectedPerformance.json';
//$selectedSessionFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/selectedSession.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";

/* Posts seats reservation request */
try {
//    methodAllowed('POST');
    session_start();

    // TODO: check if set
    /* as
         {
            pData: {
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
            }[],
            maxRows: number
        }
    */
    $personalData = $_SESSION['personalData'];

//    $personalData = json_decode(file_get_contents($personalDataFilePath))->pData;

    $selectedPerformanceId = $_SESSION['selectedPerformanceId'];
    $selectedSessionId = $_SESSION['selectedSessionId'];

//    $selectedPerformance = json_decode(file_get_contents($selectedPerformanceFilePath));
//    print_r($selectedPerformance);

//    $selectedSession = json_decode(file_get_contents($selectedSessionFilePath));
//    print_r($selectedSession);

    $performances = json_decode(file_get_contents($dataFilePath));
    $performance = $performances[$selectedPerformanceId];

    $session = $performance->sessions[$selectedSessionId];

    // TODO: validate personal entities
//    print_r($personalData);
    foreach ($personalData->pData as $seatRequest) {

//        print_r($seatRequest);
//        print_r($seatRequest->seat);
//        print_r($session->seats);
//        print_r($session->seats[$seatRequest->row]);
//        print_r($session->seats[$seatRequest->row][$seatRequest->seat]);
        $seat = $session->seats[$seatRequest->row][$seatRequest->seat];
        $seat->availability = 1;
        $seat->viewer = $seatRequest->userData;
    }

    file_put_contents($dataFilePath, json_encode($performances));

    echo json_encode($session);
} catch (BaseException $e) {
    transformException($e);
}
