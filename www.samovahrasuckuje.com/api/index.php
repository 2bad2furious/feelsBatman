<?php
require "startup.php";

$url = explode("/", $_SERVER["REQUEST_URI"]);

array_shift($url);

$l = count($url) - 1;

$lang = new Language();

$s = $url[$l];

if(strpos($s,"?")) $url[$l] = explode("?",$s)[0];

if (strlen($url[$l]) == 0 && count($url) > 1) {
    unset($url[$l]);
    header("Location:/" . implode("/", $url));
}
$name = $url[0] . "Controllers";

if ($name == "Controllers") $name = "ErrorController";

array_shift($url);

if (file_exists("api/Controllers/" . $name. ".php")) $controller = new $name($url,$lang);
else $controller = new ErrorController($url,$lang);


?>