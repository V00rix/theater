<?php
/**
 * Created by PhpStorm.
 * Client: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:26 AM
 */

class Performance
{
  public $title;
  public $description;
  public $imageUrl;
  public $sessions = [];

  function __construct($title, $description, $imageUrl, $sessions) {
    $this->title = $title;
    $this->description = $description;
    $this->imageUrl = $imageUrl;
    $this->sessions = $sessions;
  }
}
