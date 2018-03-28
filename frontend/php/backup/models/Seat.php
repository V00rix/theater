<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:32 AM
 */

class Seat
{
  public $availability;

  function __construct($availability)
  {
    $this->availability = $availability;
  }
}
