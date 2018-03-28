<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:30 AM
 */

class Session
{
  public $date;
  public $seats = [];

  function __construct($date, $seats)
  {
    $this->date = $date;
    $this->seats = $seats;
  }
}
