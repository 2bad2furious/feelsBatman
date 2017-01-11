<?php

var_dump(@file_get_contents("https://eune.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/2bad2furious656464684654?api_key=RGAPI-62215098-40c6-4966-b793-87e7377027d1"));
exit();

mb_internal_encoding('UTF-8');
ini_set("session.cookie_httpsonly", 1);

spl_autoload_register(function ($class) {
    if (file_exists('M/' . $class . '.php'))
        require 'M/' . $class . '.php';
    else if (file_exists('M/language/' . $class . '.php'))
        require 'M/language/' . $class . '.php';
    else if (file_exists('M/' . $class . '.php'))
        require 'M/' . $class . '.php';
    else if (file_exists("C/" . $class . ".php"))
        require "C/" . $class . ".php";
});

try {
    Db::connect('localhost', 'riotapi', 'root', '');
} catch (PDOException $e) {
    die("Failed connecting to DB");
}

session_start();

$url = explode("/", $_SERVER["REQUEST_URI"]);
array_shift($url);

if (!isset($_SESSION["messages"])) {
    $_SESSION["messages"] = array();
}
if (substr($_SERVER["REQUEST_URI"], -1, 1) != "/") {
    Controller::redirect("/" . implode("/", $url) . "/");
}

$lang = Language::setLanguage();
