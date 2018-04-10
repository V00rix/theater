<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 09-Apr-18
 * Time: 1:22 AM
 */

namespace domain\responses;


class ErrorResponse
{
    public $error;

    function __construct($errors)
    {
        $this->error = new \stdClass();
        $this->error->message = $errors;
    }

    public static function emit($e)
    {
        http_response_code(400);
        echo json_encode(new ErrorResponse($e->getMessage()));
    }
}