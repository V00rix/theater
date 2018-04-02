<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 30-Mar-18
 * Time: 5:12 PM
 */


/** Database creation scripts */

//echo file_get_contents('../queries/CreateSchema.sql') . PHP_EOL;
//echo file_get_contents('../queries/InsertTestData.sql') . PHP_EOL;

include 'helpers/databaseConnect.php';
include 'helpers/executeSqlFile.php';

// queries
$createSchemaFilePath = '../queries/CreateSchema.sql';
$insertTestDataFilePath = '../queries/InsertTestData.sql';

executeSql($createSchemaFilePath, $mysqli);
executeSql($insertTestDataFilePath, $mysqli);

//if ($result = $mysqli->query("SELECT * FROM theater.t_performance")) {
//    printf("Select returned %d rows.\n", $result->num_rows);
//
//    /* free result set */
//    $result->close();
//}