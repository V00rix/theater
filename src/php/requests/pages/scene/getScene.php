<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/test/dist/app_data/performances.json';
$selectedSessionFilePath = $_SERVER['DOCUMENT_ROOT'] . '/test/dist/app_data/selectedSession.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/test/dist/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/test/dist/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/test/dist/php/models/Exceptions.php";

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

    if (!isset($_GET['performanceId']) || !isset($_GET['sessionTime']))
        throw new argumentMissingException();


    $performance = json_decode(file_get_contents($dataFilePath))[$_GET['performanceId']];

    $session = array_filter($performance->sessions, function ($p) {
        return $p->date == $_GET['sessionTime'];
    })[0];

    file_put_contents($selectedSessionFilePath, json_encode($_GET['sessionTime']));

    $response = new Response($performance->title, $session->date, $session->seats);

    echo json_encode($response);
} catch (baseException $e) {
    transformException($e);
}
