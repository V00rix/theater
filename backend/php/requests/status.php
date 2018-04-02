<?php
/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 28-Mar-18
 * Time: 10:31 AM
 */

include '../helpers/headers.php';
include '../domain/responses/StatusResponse.php';

use domain\responses\StatusResponse;

if(session_status() !== PHP_SESSION_ACTIVE) {
    session_start();
}

/**
 * Get application status
 */
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $response = new StatusResponse();

    if (isset($_SESSION['selected_performance'])) {
        $response->selected_performance = $_SESSION['selected_performance'];
    }
    if (isset($_SESSION['selected_session'])) {
        $response->selected_session = $_SESSION['selected_session'];
    }
    if (isset($_SESSION['selected_seats'])) {
        $response->selected_seats = $_SESSION['selected_seats'];
    }
    if (isset($_SESSION['selected_checkout'])) {
        $response->selected_checkout = $_SESSION['selected_checkout'];
    }
    if (isset($_SESSION['user'])) {
        $response->user = $_SESSION['user'];
    }
    echo json_encode($response);
}

/**
 * Update application status
 */
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $request = json_decode(file_get_contents('php://input'));

    if (isset($request->selected_performance)) {
        $_SESSION['selected_performance'] = $request->selected_performance;
    }
    if (isset($request->selected_session)) {
        $_SESSION['selected_session'] = $request->selected_session;
    }
    if (isset($request->selected_seats)) {
        $_SESSION['selected_seats'] = $request->selected_seats;
    }
    if (isset($request->selected_checkout)) {
        $_SESSION['selected_checkout'] = $request->selected_checkout;
    }
    if (isset($request->user)) {
        $_SESSION['user'] = $request->user;
    }
}