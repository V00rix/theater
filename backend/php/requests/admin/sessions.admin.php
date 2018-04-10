<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 07-Apr-18
 * Time: 5:45 PM
 */

use domain\exceptions\UnauthorizedException;
use domain\responses\admin\Order;
use domain\responses\admin\Seat;
use domain\responses\admin\Session;
use domain\responses\admin\SessionResponse;
use domain\responses\ErrorResponse;

include_once  '../../helpers/headers.php';
include_once  '../../domain/responses/admin/SessionResponse.php';
include_once '../../domain/responses/ErrorResponse.php';
include_once '../../domain/exceptions/UnauthorizedException.php';
include_once  '../../helpers/databaseConnect.php';

session_start();


class BdoSeat
{
    public $id;
    public $order;
    public $row;
    public $seat;
}

try {
// todo
//    if (!isset($_SESSION['admin'])) {
//        throw new UnauthorizedException("Unauthorized");
//    }

    $mysqli = db_connect('localhost', 'root', '', 'theater');
    mysqli_query($mysqli, "SET NAMES UTF8");

    $sessionResponse = new SessionResponse();

    if ($result = $mysqli->query("SELECT s.id AS `id`, t2.date AS `date`, tp.title AS `title`, t2.id AS `date_id` FROM t_session s
                                        JOIN t_timestamp t2 ON s.date = t2.id
                                        JOIN t_performance tp ON s.performance = tp.id;")) {
        while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {

            $session = new Session();
            $session->id = $row['id'];
            $session->date = $row['date'];
            $session->performance_title = $row['title'];

            $seats = [];

            if ($result2 = $mysqli->query("SELECT t_seat.id as `id`, t_seat.number AS `seat`, row2.number AS `row`, t_seat.`order` AS `order_id` FROM t_seat 
                                                        JOIN t_row row2 ON t_seat.row = row2.id
                                                        WHERE session = {$session->id} AND t_seat.availabillity = 'BOOKED' ORDER BY `order`;")) {
                while ($row = mysqli_fetch_array($result2, MYSQLI_ASSOC)) {
                    $seat = new BdoSeat();
                    $seat->id = $row['id'];
                    $seat->seat = $row['seat'];
                    $seat->row = $row['row'];
                    $seat->order = $row['order_id'];

                    array_push($seats, $seat);
                }
            } else {
                throw new Exception(mysqli_error($mysqli));
            }

            // filter all seats

            $arr = [];

            foreach ($seats as $key => $item) {
                $arr[$item->order][$key] = $item;
            }

            foreach ($arr as $id => $seats) {
                $order = new Order();
                $order->id = $id;

                foreach ($seats as $seat) {
                    $s = new Seat();
                    $s->id = $seat->id;
                    $s->row = $seat->row;
                    $s->seat = $seat->seat;

                    array_push($order->seats, $s);

                    if ($result2 = $mysqli->query("SELECT name, email, checkout FROM t_order 
                                                    JOIN t_website_client t ON t_order.website_email = t.email
                                                    WHERE t_order.id = {$seat->order};")) {
                        while ($row = mysqli_fetch_array($result2, MYSQLI_ASSOC)) {
                            $order->user_name = $row['name'];
                            $order->user_contact = $row['email'];
                            $order->checkout = $row['checkout'];
                        }
                    } else {
                        throw new Exception(mysqli_error($mysqli));
                    }
                }

                array_push($session->orders, $order);
            }
            array_push($sessionResponse->sessions, $session);
        }
    } else {
        throw new Exception(mysqli_error($mysqli));
    }

    echo json_encode($sessionResponse);
} catch (UnauthorizedException $e) {
    $e->emit();
} catch (Exception $e) {
    ErrorResponse::emit($e);
}