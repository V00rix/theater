<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 09-Apr-18
 * Time: 12:23 PM
 */

namespace domain\exceptions;

//include_once '../responses/ErrorResponse.php';

use domain\responses\ErrorResponse;

class UnauthorizedException extends \Exception
{
    function emit()
    {
        http_response_code(401);
        echo json_encode(new ErrorResponse($this->getMessage()));
    }
}