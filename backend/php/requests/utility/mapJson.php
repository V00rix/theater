<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 07-Apr-18
 * Time: 6:40 PM
 */


include '../../helpers/headers.php';
include '../../helpers/databaseConnect.php';
include '../../domain/Credentials.php';
include '../../domain/exceptions/UnauthorizedException.php';
include '../../domain/responses/ErrorResponse.php';

use domain\Credentials;
use domain\exceptions\UnauthorizedException;
use domain\responses\ErrorResponse;


try {
    // todo
    //    if (!isset($_SESSION['admin'])) {
    //        throw new UnauthorizedException("Unauthorized");
    //    }

    $jsonPerformances = json_decode(file_get_contents('../../../app_data/performances.json'));

    $mysqli = db_connect();

    // ... clear

    if (!$mysqli->query("DELETE FROM t_performance;")) {
        throw new Exception(mysqli_error($mysqli));
    }

    if (!$mysqli->query("DELETE FROM t_timestamp;")) {
        throw new Exception(mysqli_error($mysqli));
    }

    if (!$mysqli->query("DELETE FROM t_session;")) {
        throw new Exception(mysqli_error($mysqli));
    }

    if (!$mysqli->query("DELETE FROM t_order;")) {
        throw new Exception(mysqli_error($mysqli));
    }

    if (!$mysqli->query("DELETE FROM t_seat WHERE availabillity != 'HIDDEN';")) {
        throw new Exception(mysqli_error($mysqli));
    }


    if (!$mysqli->query("DELETE FROM t_website_client;")) {
        throw new Exception(mysqli_error($mysqli));
    }

    if (!$mysqli->query("DELETE FROM t_order;")) {
        throw new Exception(mysqli_error($mysqli));
    }



    // insert
    mysqli_query($mysqli, "SET NAMES UTF8");

    foreach ($jsonPerformances as $performance) {
        if (!$mysqli->query("INSERT INTO t_performance (author, title, image_url, description) 
                                VALUES ('Sample Author', '{$performance->title}', '{$performance->imageUrl}', '{$performance->description}');")) {
            throw new Exception(mysqli_error($mysqli));
        }
        $performance_id = $mysqli->insert_id;


        foreach ($performance->sessions as $session) {
            $date = date("Y-m-d H:i:s", $session->date / 1000 - 60 * 60 * 2 - 26);

            if (!$mysqli->query("INSERT INTO t_timestamp (date) 
                                VALUES ('{$date}')")) {
                throw new Exception(mysqli_error($mysqli));
            }
            $timestamp_id = $mysqli->insert_id;

            if (!$mysqli->query("INSERT INTO t_session (date, hall, performance)
                                VALUES ({$timestamp_id}, 1, {$performance_id});")) {
                throw new Exception(mysqli_error($mysqli));
            }
            $session_id = $mysqli->insert_id;

            $viewers = [];
            $all_seats = array();

            $session_seats_length = count($session->seats);
            foreach ($session->seats as $r => $row) {
                foreach ($row as $s => $seat) {
                    if ($seat->availability === 1 || $seat->availability === 2) {
                        $new_seat = new stdClass();

                        $new_seat->row = $session_seats_length - $r;
                        $new_seat->seat = $s + 1;
                        $new_seat->availability = $seat->availability;

                        $contact = 'KEK';

                        if (isset($seat->viewer->phone)) {
                            if (!array_key_exists($contact = $seat->viewer->phone, $viewers)) {
                                $viewers[$seat->viewer->phone] = new stdClass();
                                $viewers[$seat->viewer->phone]->name = $seat->viewer->name;
                                $viewers[$seat->viewer->phone]->seats = [];
                            }
                        } elseif (isset($seat->viewer->email)) {
                            if (!array_key_exists($contact = $seat->viewer->email, $viewers)) {
                                $viewers[$seat->viewer->email] = new stdClass();
                                $viewers[$seat->viewer->email]->name = $seat->viewer->name;
                                $viewers[$seat->viewer->email]->seats = [];
                            }
                        }

                        array_push($viewers[$contact]->seats, $new_seat);
                    }
                }
            }

//            print_r($viewers);


            $i = 0;
            foreach ($viewers as $contact => $data) {
                $d = date("Y-m-d H:i:s", mktime(0, 0, $i, 7, 1, 2000));
                if (!$mysqli->query("INSERT INTO t_timestamp (date) 
                                VALUES ('{$d}')")) {
                    throw new Exception(mysqli_error($mysqli));
                }
                $now = $mysqli->insert_id;

                if (!$mysqli->query("INSERT INTO t_website_client (email, name)
                                VALUES ('{$contact}', f{$data->name}irstName);")) {
                    throw new Exception(mysqli_error($mysqli));
                }

                if (!$mysqli->query("INSERT INTO t_order
                                        (date, website_email, checkout, confirmed)
                                VALUES ({$now}, '{$contact}', 'PAY_BEFORE', TRUE);")) {
                    throw new Exception(mysqli_error($mysqli));
                }
                $order_id = $mysqli->insert_id;

                foreach ($data->seats as $seat) {
//                    print_r('ROW '. $seat->seat . '<br>');
//                    print_r( '' .$seat->row. '<br>');


                    if (!$mysqli->query("INSERT INTO t_seat (number, row, `order`, session, availabillity)
                              SELECT {$seat->seat}, r2.id, {$order_id}, {$session_id}, 'BOOKED' FROM t_row r2 WHERE r2.number = {$seat->row}")) {
                        throw new Exception(mysqli_error($mysqli));
                    }
                }

//                print_r($filtered);

//                foreach ($filtered as $seat) {
//                    print_r($seat);
//                }
                $i += 5;
            }

        }
    }

    foreach ([4, 5, 6, 7, 34, 35, 36, 37] as $seat) {
//        echo $seat;
        if (!$mysqli->query("INSERT INTO t_seat (number, row, session, availabillity) SELECT
                                                           {$seat}   AS `seat`,
                                                           r.id     AS row_id,
                                                           s.id     AS `session_id`,
                                                           'HIDDEN' AS `availability`
                                                         FROM t_row r
                                                           STRAIGHT_JOIN t_session s
                                                         WHERE r.number = 14 OR r.number = 13 OR r.number = 12;")) {
            throw new Exception(mysqli_error($mysqli));
        }
    }


//    print_r($jsonPerformances);


} catch (UnauthorizedException $e) {
    $e->emit();
} catch (Exception $e) {
    ErrorResponse::emit($e);
}