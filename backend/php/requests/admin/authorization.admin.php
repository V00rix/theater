<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 07-Apr-18
 * Time: 5:45 PM
 */

include '../../helpers/headers.php';
include '../../domain/Credentials.php';
include '../../domain/responses/ErrorResponse.php';

use domain\Credentials;
use domain\responses\ErrorResponse;

session_start();

try {
    if (!$_SERVER['REQUEST_METHOD'] === 'POST')
        throw new Exception('Only POST is allowed');

    $params = json_decode(file_get_contents('php://input'));

    if (!isset($params->login) || !isset($params->password)) {
        throw new Exception('Both login and password are required');
    }

    $credentials = json_decode(file_get_contents('../../../app_data/credentials.json'));

    if ($params->login !== $credentials->login || !password_verify($params->password, $credentials->password)) {
        throw new Exception('Wrong credentials');
    }

    $_SESSION['admin'] = $params->login;
    echo json_encode($_SESSION['admin']);
} catch (Exception $e) {
    ErrorResponse::emit($e);
}