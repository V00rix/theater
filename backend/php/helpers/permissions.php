<?php

include '../../domain/exceptions/UnauthorizedException.php';

use domain\exceptions\UnauthorizedException;

function restricted()
{
    if (!isset($_SESSION['admin'])) {
        (new UnauthorizedException("Unauthorized"))->emit();
        die();
    }
}