<?php
/**
 * Owner: Augusto Flores
 * This PHP page is create to interact with the test cases, this get the request parameters cookies and
 * any other information then response with a JSON object with all structure of teh data for the request.
 * this page should be put in the local environment.
 * The page not only return the data related to the request, this create a <method>_index.json file
 * with the data of the request.
 * We have the form.html, this is a page with forms create tp interact with this file and see the current behavior
 * and evaluate the current page.
 */
session_start();

//-------------------------------------------------------
$params = null;

$time = $currentDate = new DateTime();
$time = $time->format('Y-m-d H:i:s');
$statusCode = 404;
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // collect value of input field
    $params = $_POST;
    $statusCode = 201;
} else if ($_SERVER["REQUEST_METHOD"] == "GET") {
    // collect value of input field
    $params = $_GET;
    $statusCode = 200;
}

if( isset($params['session'] )){
    if( strcmp($params['session'],'login') == 0 ){
        $_SESSION['user'] = $params['user'];
    }else if( strcmp($params['session'],'logout') == 0 ){
        session_destroy();
    }
}

$parameters = array();
if( $params != null ){
    foreach( $params as $k => $v){
        $parameters[$k] = $v ;
    }

}

$data = array(
    'method' => $_SERVER["REQUEST_METHOD"],
    'time' => $time,
);
//---------------- Cookies verification -----------------

$cookies = $_COOKIE;
//// add the cokies values for each request in a file,
//if( $cookies != null ){
//    if( count( $cookies ) > 0) {
//        $fp = fopen('cookies.txt', 'a');//opens file in append mode
//        fwrite($fp, ' ---------------'.$_SERVER["REQUEST_METHOD"].PHP_EOL);
//        foreach ($cookies as $key => $value) {
//            fwrite($fp, $key.' = '.$value.PHP_EOL);
//        }
//        fclose($fp);      }
//}

//---------------------------
$data['hasUser'] = isset($params['user']);
$data['logged'] = isset($_SESSION['user']);
$data['parameters'] = $parameters;

//---------------------------

$jsonData = file_get_contents('php://input');

if($jsonData != null ){
    $jsonData = trim($jsonData);
    if( strlen($jsonData) > 0 ) {
        $jsonData = json_decode($jsonData, true);
    }else{
        $jsonData = null;
    }
}else{
    $jsonData = null;
}

$data['json'] = $jsonData;
$data['cookiesRequest'] = $cookies;



//--- set cookie
$cookie_name = "cookie_user";
$cookie_value = "John Doe";
$duration = time() + 60*60;// <- 5 minutes //(86400 * 30); // 30 days -> 86400 = 1 day
// writing a cookie
if( !isset($cookies[$cookie_name])) {
    setcookie($cookie_name, $cookie_value, $duration, "/"); // 86400 = 1 day
}
//------------------------------

$json = json_encode($data);

http_response_code($statusCode);
header("Expires: Mon, 26 Jul 1997 05:00:00 GMT");
header("Cache-Control: no-cache");
header("Content-Type: application/json;charset=utf-8");
//header("Content-type:application/pdf");
header("Pragma: no-cache");

try {
    $outFile = $_SERVER["REQUEST_METHOD"] ."_index.json";
    unlink($outFile);
    $myfile = fopen($outFile, "w");
    fwrite($myfile, $json);
    fclose($myfile);
}catch (Exception $e){
}

echo $json;

