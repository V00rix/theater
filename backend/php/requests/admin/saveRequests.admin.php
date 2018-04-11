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

include_once '../../helpers/headers.php';
include_once '../../domain/responses/admin/SessionResponse.php';
include_once '../../domain/responses/ErrorResponse.php';
include_once '../../domain/exceptions/UnauthorizedException.php';
include_once '../../helpers/databaseConnect.php';

session_start();

try {
// todo
//    if (!isset($_SESSION['admin'])) {
//        throw new UnauthorizedException("Unauthorized");
//    }
    $mysqli = db_connect();

    if (!$_SERVER['REQUEST_METHOD'] === 'POST')
        throw new Exception('Only POST is allowed');

    $params = json_decode(file_get_contents('php://input'));

    $params = array_filter($params, function ($order) {
        return $order->isConfirmed !== null;
    });

    foreach ($params as $order) {
        $state = $order->isConfirmed === true ? 'TRUE' : 'FALSE';

        if (!$mysqli->query("UPDATE t_order 
                            SET confirmed = {$state}
                            WHERE id = {$order->code};")) {
            throw new Exception(mysqli_error($mysqli));
        }

        if ($order->isConfirmed === false) {
            foreach ($order->seats as $seat) {
                if (!$mysqli->query("DELETE FROM t_seat WHERE id = {$seat->id};")) {
                    throw new Exception(mysqli_error($mysqli));
                }
            }
        }
    }

    echo json_encode($params);
} catch (UnauthorizedException $e) {
    $e->emit();
} catch (Exception $e) {
    ErrorResponse::emit($e);
}