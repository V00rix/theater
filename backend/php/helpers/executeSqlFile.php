<?php
/**
 * Created by IntelliJ IDEA.
 * User: vlado
 * Date: 30-Mar-18
 * Time: 5:26 PM
 */

/**
 * Executes sql query from file
 * @param $sqlFileToExecute
 * @param $mysqli
 */
function executeSql($sqlFileToExecute, &$mysqli)
{
    $f = fopen($sqlFileToExecute, "r+");
    $sqlFile = fread($f, filesize($sqlFileToExecute));
    $sqlArray = explode(';', $sqlFile);

//    var_dump($sqlArray);

    foreach ($sqlArray as $stmt) {
        echo "$stmt" . PHP_EOL;

        if (strlen($stmt) > 3 && substr(ltrim($stmt), 0, 2) != '/*') {
            $result = $mysqli->query($stmt . ';');
            if (!$result) {
                $sqlErrorCode = mysqli_errno($mysqli);
                $sqlErrorText = mysqli_error($mysqli);
                $sqlStmt = $stmt;
                echo "An error occurred during query!<br/>";
                break;
            }
        }
    }

    if ($sqlErrorCode == 0) {
        echo "Script is executed successfully!";
    } else {
        echo "Error code: $sqlErrorCode<br/>";
        echo "Error text: $sqlErrorText<br/>";
        echo "Statement:<br/> $sqlStmt<br/>";
    }

}