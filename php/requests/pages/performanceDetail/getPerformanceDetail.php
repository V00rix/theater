<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

//header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';
//$selectedPerformanceFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/selectedPerformance.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/validations/serverMethod.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";

/* Get performance title, description imageUrl and sessions */

class Response
{
    public $title;
    public $imageUrl;
    public $description;
    public $sessions;

    public function __construct($title, $imageUrl, $description, $sessions)
    {
        $this->title = $title;
        $this->imageUrl = $imageUrl;
        $this->description = $description;
        $this->sessions = $sessions;
    }
}

try {
    methodAllowed('GET');

    if (!isset($_GET['performanceId']))
        throw new argumentMissingException("Missing 'performanceId'");

    // validate boundaries
//    if ()

    session_start();
    $_SESSION['selectedPerformanceId'] = $_GET['performanceId'];

    $performance = json_decode(file_get_contents($dataFilePath))[$_GET['performanceId']];

    $response = new Response($performance->title, $performance->imageUrl, $performance->description,
        array_map(function ($session) {
            return $session->date;
        }, $performance->sessions));

    echo json_encode($response);
} catch (BaseException $e) {
    transformException($e);
}
