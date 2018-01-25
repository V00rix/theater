<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

header("Access-Control-Allow-Origin: *");

$dataFilePath = $_SERVER['DOCUMENT_ROOT'] . '/test/dist/app_data/performances.json';

require_once $_SERVER['DOCUMENT_ROOT'] . "/test/dist/php/helpers/transformException.php";
require_once $_SERVER['DOCUMENT_ROOT'] . "/test/dist/php/validations/serverMethod.php";
/* Get performances titles and imageUrls */
class Response {
    public $title;
    public $imageUrl;

    public function __construct($title, $imageUrl)
    {
        $this->title = $title;
        $this->imageUrl = $imageUrl;
    }
}

try {
    methodAllowed('GET');

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


