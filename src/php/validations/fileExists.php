<?php
/**
 * Created by PhpStorm.
 * User: vlado
 * Date: 24-Jan-18
 * Time: 4:31 PM
 *
/* Checks for file existence and then performs related action */
// includes
$root = $_SERVER['DOCUMENT_ROOT'] . '/theater/src/php/';
// classes
include_once $root . 'models/exceptions.php';
// classes
include_once $root . '/classes/Exceptions/exceptions.php';
// check for file existence
function fileExists($filePath) {
    if ( !file_exists($filePath) )
        throw new fileNotFoundException("File " . $filePath ." was not found");
}