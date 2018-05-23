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
include_once  '../../helpers/databaseConnect.php';
include_once  '../../helpers/permissions.php';

session_start();


class BdoSeat
{
    public $id;
    public $order;
    public $row;
    public $seat;
}

try {
    restricted();

    $mysqli = db_connect();

    $response = new stdClass();
    $response->performances = [];

    if ($result = $mysqli->query("SELECT * FROM t_performance;")) {
        while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
            $performance = new stdClass();
            $performance->id = $row['id'];
            $performance->author = $row['author'];
            $performance->title = $row['title'];
            $performance->description = $row['description'];
            $performance->image_url = $row['image_url'];
            array_push($response->performances, $performance);
        }
    } else {
        throw new Exception(mysqli_error($mysqli));
    }

    echo json_encode($response);
} catch (Exception $e) {
    ErrorResponse::emit($e);
}