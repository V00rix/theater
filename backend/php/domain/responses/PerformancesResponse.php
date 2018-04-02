<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 31-Mar-18
 * Time: 2:43 PM
 */

namespace domain\responses;


class PerformancesResponse
{
    public $performances;
    public $maximum_seats;

    function __construct(&$performances, $maximum_seats)
    {
        $this->performances = $performances;
        $this->maximum_seats = $maximum_seats;
    }

    function __destruct()
    {
        unset($this->performances);
    }
}