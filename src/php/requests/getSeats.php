<?php
/**
 * Created by PhpStorm.
 * User: vlado
 * Date: 21-Jan-18
 * Time: 2:32 PM
 */


//header("Access-Control-Allow-Origin: *");
//header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");
// TODO: i think session resetting has smth do do with origin and stuff
// Get to save seats to session
session_start();
echo(json_encode($_SESSION["seats"]));