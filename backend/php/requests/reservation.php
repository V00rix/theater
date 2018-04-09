<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 01-Apr-18
 * Time: 12:01 AM
 */

include '../helpers/headers.php';
include '../helpers/databaseConnect.php';
include '../dao/PerformancesDao.php';
include '../domain/responses/PerformancesResponse.php';

use dao\PerformancesDao;
use domain\enumeration\Availabillity;
use domain\responses\PerformancesResponse;

if (session_status() !== PHP_SESSION_ACTIVE) {
    session_start();
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

//    $mysqli = db_connect();
    $mysqli = db_connect('localhost', 'root', '', 'theater');

    $performances = PerformancesDao::get($mysqli);

    $selected_performance = $_SESSION['selected_performance'];
    $selected_session = $_SESSION['selected_session'];
    $selected_seats = $_SESSION['selected_seats'];
    $user = $_SESSION['user'];
    $selected_checkout = $_SESSION['selected_checkout'];

    // todo: check for performance, session, user format etc.

    $rows = count($performances[$selected_performance]->sessions[$selected_session]->seats);

    foreach ($selected_seats as $seat) {

        // todo: validation (boundaries, availability)
        $performances[$selected_performance]->sessions[$selected_session]->seats[$seat->row - 1][$seat->seat - 1] = Availabillity::BOOKED;
    }

    // After validation ok
    $timestamp_id = null;
    $client_email = null;
    $registered_email = null;
    $order_id = null;
    $seat_id = null;
    $ticket_id = null;

    try {

        /**
         * Create timestamp
         */
        if ($mysqli->query("INSERT INTO t_timestamp (date) VALUES (NOW())") === TRUE) {
            $timestamp_id = $mysqli->insert_id;
        } else {
            throw new Exception('Could not create timestamp');
        }

        /**
         * Get website client email
         */
        if (($result = $mysqli->query("SELECT email FROM t_website_client WHERE email = '{$user->contact}';")) && $result->num_rows > 0) {
            $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
            $client_email = $row['email'];
        } /**
         * Create client email
         */
        else {
            $client_email = $user->contact;
            if (!($result = $mysqli->query("INSERT INTO t_website_client (email, name) VALUES ('{$user->contact}', '{$user->name}');") === TRUE)) {
                throw new Exception('Could not create email');
            }

        }

        /**
         * Reference registered user if exists
         */
        if ($result = $mysqli->query("SELECT email FROM t_registered_user WHERE email = {$user->contact}") === TRUE) {
            $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
            $registered_email = $row['email'];
        }

        /**
         * Create order
         */
        if ($result = $mysqli->query("INSERT INTO t_order (date, registered_email, website_email, checkout)
          VALUES ({$timestamp_id}, " . ($registered_email ? $registered_email : "null") . ", '{$client_email}', 'SELF_CHECKOUT');") === TRUE) {
            $order_id = $mysqli->insert_id;
        } else {
            throw new Exception('Could not create order');
        }


        /**
         * Create seat
         */
        foreach ($selected_seats as $seat) {

//            echo "seat: " . $seat->seat;
//            echo "row: " . $seat->row;
//            echo "session " . $performances[$selected_performance]->sessions[$selected_session]->id;
//            echo "order " . $order_id;

            if ($mysqli->query(
                    "INSERT INTO t_seat (number, row, session, availabillity, `order`) 
                    SELECT {$seat->seat}, id, {$performances[$selected_performance]->sessions[$selected_session]->id}, 'BOOKED', {$order_id}
                    FROM t_row 
                    WHERE number = {$seat->row}") === TRUE) {
                $seat_id = $mysqli->insert_id;

            } else {
                throw new Exception(mysqli_error($mysqli));
            }
        }

        $_SESSION['selected_seats'] = [];

        echo json_encode(dechex($order_id));

    } catch (Exception $e) {
        header("400");
        echo $e->getMessage();
    }
}