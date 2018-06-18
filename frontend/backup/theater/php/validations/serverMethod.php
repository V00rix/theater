<?php
/**
 * Created by PhpStorm.
 * Client: Client
 * Date: 24-Jan-18
 * Time: 11:54 AM
 *
/* Validation for allowed server method */
// classes
require_once $_SERVER['DOCUMENT_ROOT'] . "/theater/php/models/Exceptions.php";
// checks if method is allowed
// throws a methodNotAllowedException if not
function methodAllowed($method) {
    if ($_SERVER['REQUEST_METHOD'] !== $method)
        throw new methodNotAllowedException("Method '" . $_SERVER['REQUEST_METHOD'] ."' is not allowed.");
}