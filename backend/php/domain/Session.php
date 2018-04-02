<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 31-Mar-18
 * Time: 12:51 PM
 */

namespace domain;


class Session
{
    public $id;
    public $date;
    public $seats;
    public $hall;


    function __construct()
    {
        $this->seats = [];
    }

    function __destruct()
    {
        unset($this->seats);
    }
}