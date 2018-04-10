<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 07-Apr-18
 * Time: 6:40 PM
 */


include '../../helpers/headers.php';
include '../../domain/Credentials.php';

use domain\Credentials;

$password = password_hash("rodip_321ahsap", PASSWORD_DEFAULT);
$credentials = new Credentials('Kotova228TheBest', $password);

file_put_contents('../../../app_data/credentials.json' ,json_encode($credentials));

echo file_get_contents('../../../app_data/credentials.json');