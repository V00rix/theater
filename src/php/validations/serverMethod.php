<?php
/**
 * Created by PhpStorm.
 * User: User
 * Date: 24-Jan-18
 * Time: 11:54 AM
 *
/* Validation for allowed server method */
// includes
$root = $_SERVER['DOCUMENT_ROOT'] . '/test/dist/php/';
// classes
include_once $root . 'models/Exceptions.php';
// checks if method is allowed
// throws a methodNotAllowedException if not
function methodAllowed($method) {
    if ($_SERVER['REQUEST_METHOD'] !== $method)
        throw new methodNotAllowedException("Method '" . $_SERVER['REQUEST_METHOD'] ."' is not allowed.");
}