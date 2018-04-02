<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 01-Apr-18
 * Time: 11:18 AM
 */

namespace dao;

include '../domain/Performance.php';
include '../domain/Session.php';
include '../domain/enumeration/Availabillity.php';

use domain\enumeration\Availabillity;
use domain\Performance;
use domain\Session;

abstract class PerformancesDao
{
    /**
     * Map performances
     * @param $mysqli
     * @return array
     */
    public static function get(&$mysqli)
    {
        $performances = [];

        if ($result = $mysqli->query("SELECT id, title, image_url, description FROM t_performance;")) {
            while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
                $performance = new Performance();
                $performance->id = $row['id'];
                $performance->title = $row['title'];
                $performance->image_url = $row['image_url'];
                $performance->description = $row['description'];

                array_push($performances, $performance);
            }

            $result->close();
        }

        foreach ($performances as $p) {
            if ($result = $mysqli->query("SELECT t_session.id, t2.date, hall FROM t_session JOIN t_performance tp ON t_session.performance = 1 JOIN t_timestamp t2 ON t_session.date = t2.id;")) {

                while ($row = mysqli_fetch_array($result)) {
                    $session = new Session();
                    $session->id = $row['id'];
                    $session->date = $row['date'];
                    $session->hall = $row['hall'];

                    array_push($p->sessions, $session);

                }
                $result->close();
            }

            foreach ($p->sessions as $s) {
                if ($result2 = $mysqli->query("SELECT number, seat_number
 FROM t_row 
 WHERE hall = {$s->hall} 
 ORDER BY number;"
                )) {

                    $x = 0;
                    while ($row = mysqli_fetch_array($result2, MYSQLI_ASSOC)) {
                        array_push($s->seats, []);
                        for ($j = 1; $j <= $row['seat_number']; $j++) {
                            array_push($s->seats[$x], Availabillity::FREE);
                        }
                        $x++;
                    }
                    $result2->close();
                }

                if ($result3 = $mysqli->query("SELECT
  t_seat.number as `seat`,
  row2.number as `row`,
  availabillity
FROM t_seat
  JOIN t_row row2 ON t_seat.row = row2.id
  WHERE t_seat.session = {$s->id}
ORDER BY availabillity DESC, session, row, t_seat.number")) {
                    while ($row = mysqli_fetch_array($result3, MYSQLI_ASSOC)) {
                        $s->seats[$row['row'] - 1][$row['seat'] - 1] = $row['availabillity'];
                    }
                    $result3->close();
                }

            }
        }

        return $performances;
    }

    public static function saveOrUpdate(&$mysqli, $performances) {

    }
}