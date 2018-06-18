<?php
/**
 * Created by PhpStorm.
 * Client: vlado
 * Date: 24-Jan-18
 * Time: 4:31 PM
 *
/* Checks for file existence and then performs related action */
// includes
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/exceptions.php";
// check for file existence
function fileExists($filePath) {
    if ( !file_exists($filePath) )
        throw new fileNotFoundException("File " . $filePath ." was not found");
}