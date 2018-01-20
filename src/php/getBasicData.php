<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 2:25 AM
 */

foreach (glob('models/*.php') as $filename)
{
  require $filename;
}

// Get data from DB
$data = [
  new Performance(
    'SomeWord',
    null,
    'https://wallpapercave.com/wp/nsu3cSp.jpg',
    null),
  new Performance(
    'Short title',
    null,
    'https://hdwallsource.com/img/2014/8/green-background-pictures-17225-17781-hd-wallpapers.jpg',
    null),
  new Performance(
    'Title with some words',
    null,
    'http://www.creativehdwallpapers.com/uploads/large/background/landscape-nature-background-image.jpg',
    null),
  new Performance(
    'And thus we fall',
    null,
    'https://www.forevergeek.com/wp-content/media/2013/12/SpaceTravel2.jpg',
    null)
];

header("Access-Control-Allow-Origin: *");
echo(json_encode($data));
