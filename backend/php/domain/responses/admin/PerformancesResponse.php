<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 31-Mar-18
 * Time: 2:43 PM
 */

namespace domain\responses\admin\performances;


class PerformancesResponse
{
    public $orders;

    function __construct()
    {
        $this->orders = [];
    }

    function __destruct()
    {
        unset($this->orders);
    }
}

class Order {
    public $code;
    public $checkout;
    public $user_name;
    public $user_contact;
    public $performance_title;
    public $session_date;
    public $seats;

    function __construct()
    {
        $this->seats = [];
    }

    function __destruct()
    {
        unset($this->seats);
    }
}

class Seat {
    public $row;
    public $seat;
}