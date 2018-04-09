<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 07-Apr-18
 * Time: 5:45 PM
 */

include '../../helpers/headers.php';

session_start();

if (isset($_SESSION['admin']))
    unset($_SESSION['admin']);