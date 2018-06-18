<?php
/**
 * Created by PhpStorm.
 * Client: Client
 * Date: 24-Jan-18
 * Time: 11:48 AM
 */

/* Transform exception to http responses */
/* includes */
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";
// Transform errors to http responses
function transformException($e) {
    try {
        throw $e;
    }
    catch (baseException $e) {
        $e->header();
        echo $e->getMessage();
    }
    catch (Exception $e) {
        header("HTTP/1.1 500 Internal Server Error");
        echo $e->getMessage();
    }
}