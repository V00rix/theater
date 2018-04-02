<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 31-Mar-18
 * Time: 12:45 PM
 */

namespace domain;


class Performance
{
    public $id;
    public $title;
    public $image_url;
    public $description;
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