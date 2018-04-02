<?php

include '../helpers/headers.php';
include '../helpers/databaseConnect.php';
include '../domain/responses/PerformancesResponse.php';
include '../dao/PerformancesDao.php';

use dao\PerformancesDao;
use domain\responses\PerformancesResponse;


/**
 * Get performances data
 */
if ($_SERVER['REQUEST_METHOD'] === 'GET') {

// TODO: change to select from theater

    $performances = [];

    $mysqli = db_connect();

    $performances = PerformancesDao::get($mysqli);

    $performancesResponse = new PerformancesResponse($performances, 5);

    echo(json_encode($performancesResponse));
}