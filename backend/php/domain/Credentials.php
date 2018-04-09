<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 07-Apr-18
 * Time: 6:45 PM
 */

namespace domain;


class Credentials
{
    public $login;
    public $password;

    /**
     * Credentials constructor.
     * @param $l string Login
     * @param $p string Password
     */
    function __construct($l, $p)
    {
        $this->login = $l;
        $this->password = $p;
    }
}