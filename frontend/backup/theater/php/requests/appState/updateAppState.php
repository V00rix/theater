<?php
/**
 * Created by PhpStorm.
 * Client: Client
 * Date: 24-Jan-18
 * Time: 12:12 PM
 */
try {
    methodAllowed('POST');

    $params = json_decode(file_get_contents('php://input'));

    session_open();
    $_SESSION['appState'] = $params->appState;

    echo('Success');

} catch (baseException $e) {
    transformException($e);
}