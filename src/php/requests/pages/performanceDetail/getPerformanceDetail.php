<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/theater/app_data/performances.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/src/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/src/php/validations/serverMethod.php";

/* Get performance title, description imageUrl and sessions */
class Response {
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

    var_dump($_GET['performanceId']);

    $performances = json_decode(file_get_contents($dataFilePath));

    $response = [];

    foreach ($performances as $performance) {
        array_push($response, new Response($performance->title, $performance->imageUrl));
    }

    echo json_encode($response);
} catch (baseException $e) {
    transformException($e);
}

foreach (glob('models/*.php') as $filename)
{
    require $filename;
}


