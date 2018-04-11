<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 07-Apr-18
 * Time: 5:45 PM
 */


use domain\responses\admin\performances\Order;
use domain\responses\admin\performances\PerformancesResponse;
use domain\responses\admin\performances\Seat;

include '../../helpers/headers.php';
include '../../domain/responses/admin/PerformancesResponse.php';
include '../../domain/responses/ErrorResponse.php';
include '../../helpers/databaseConnect.php';

session_start();

// todo
//if (isset($_SESSION['admin'])) {
try {
    $mysqli = db_connect();
    mysqli_query($mysqli, "SET NAMES UTF8");

    $performancesResponse = new PerformancesResponse();
    $result = 1;
    if ($result = $mysqli->query("SELECT id, checkout, t.name,  t.email FROM t_order
                                          JOIN t_website_client t ON t_order.website_email = t.email
                                        WHERE t_order.confirmed IS NULL")) {
        while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
            $order = new Order();
            $order->code = $row['id'];
            $order->checkout = $row['checkout'];
            $order->user_name = $row['name'];
            $order->user_contact = $row['email'];

            if ($result2 = $mysqli->query("SELECT t_seat.id as `id`, t_seat.number as `seat`, row2.number as `row`, timestamp2.date, tp.title
                                                FROM t_seat
                                                  JOIN t_row row2 ON t_seat.row = row2.id
                                                  JOIN t_session session2 ON t_seat.session = session2.id
                                                  JOIN t_timestamp timestamp2 ON session2.date = timestamp2.id
                                                  JOIN t_performance tp ON session2.performance = tp.id
                                                WHERE t_seat.`order` = {$order->code};")) {
                while ($row = mysqli_fetch_array($result2, MYSQLI_ASSOC)) {
                    $seat = new Seat();
                    $seat->id = $row['id'];
                    $seat->row = $row['row'];
                    $seat->seat = $row['seat'];
                    $order->session_date = $row['date'];
                    $order->performance_title = $row['title'];
                    array_push($order->seats, $seat);
                }
            }

            array_push($performancesResponse->orders, $order);
        }
    }

    echo json_encode($performancesResponse);
}
catch (Exception $e) {
    \domain\responses\ErrorResponse::emit($e);
}