<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 17-Apr-18
 * Time: 10:03 PM
 */


use domain\exceptions\UnauthorizedException;
use domain\responses\admin\Session;
use domain\responses\admin\SessionResponse;
use domain\responses\ErrorResponse;

include_once '../../helpers/headers.php';
include_once '../../domain/responses/admin/SessionResponse.php';
include_once '../../domain/responses/ErrorResponse.php';
include_once '../../helpers/databaseConnect.php';
include_once '../../helpers/permissions.php';

session_start();

try {
restricted();

    $mysqli = db_connect();

    if (!$_SERVER['REQUEST_METHOD'] === 'POST')
        throw new Exception('Only POST is allowed');

    $response = new SessionResponse();

    if ($mysqli->query("INSERT INTO t_timestamp (date) VALUES (NOW())") === TRUE) {
        $timestamp_id = $mysqli->insert_id;

        if ($mysqli->query("INSERT INTO t_session  (date, hall, performance) SELECT {$timestamp_id}, h.id, p.id FROM t_hall h STRAIGHT_JOIN t_performance p ORDER BY p.id, h.id LIMIT 1")) {
            $session_id = $mysqli->insert_id;

            if ($result = $mysqli->query("SELECT t_session.id, t2.date, hall, title FROM t_session 
                                                JOIN t_timestamp t2 ON t_session.date = t2.id 
                                                JOIN t_performance tp ON t_session.performance = tp.id
                                                WHERE t_session.id = {$session_id}")) {
                while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
                    $session = new Session();
                    $session->id = $row['id'];
                    $session->date = $row['date'];
                    $session->hall = $row['hall'];
                    $session->performance_title = $row['title'];
                    array_push($response->sessions, $session);
                }
            }

        } else {
            throw new Exception(mysqli_error($mysqli));
        }

    } else {
        throw new Exception('Could not create timestamp');
    }

    echo json_encode($response);

} catch (Exception $e) {
    ErrorResponse::emit($e);
}