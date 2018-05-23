<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 31-Mar-18
 * Time: 2:43 PM
 */

namespace domain\responses\admin;


class SessionResponse
{
    public $sessions;

    function __construct()
    {
        $this->sessions = [];
    }

    function __destruct()
    {
        unset($this->sessions);
    }
}

class Session {
    public $id;
    public $performance_title;
    public $performance_id;
    public $date;
    public $orders;
    public $hall;

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
    public $id;
    public $checkout;
    public $user_name;
    public $user_contact;
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
    public $id;
    public $row;
    public $seat;
}