<?php
ini_set("session.cookie_httpsonly", 1);

spl_autoload_register(function ($class) {
    if (file_exists('Models/' . $class . '.php')) require 'Models/' . $class . '.php';
    else if (file_exists('Models/form/' . $class . '.php')) require_once 'Models/form/' . $class . '.php';
    else if (file_exists('Models/system/' . $class . '.php')) require_once 'Models/system/' . $class . '.php';
    else if (file_exists("Controllers/" . $class . ".php")) require_once "Controllers/" . $class . ".php";
    else if (file_exists("Controllers/system/" . $class . ".php")) require_once "Controllers/system/" . $class . ".php";
    else if (file_exists("Controllers/admin/" . $class . ".php")) require_once "Controllers/admin/" . $class . ".php";
});
try {
    Db::pripoj("localhost","samsgame","root","");
    /*Db::pripoj('wm73.wedos.net', 'd82268_game', 'a82268_game', '!7tu53AZ[');*/
} catch (PDOException $e) {
    die("Failed connecting to DB");
}
session_start();
mb_internal_encoding('UTF-8');

if(isset($_SESSION["language"])){
    $lang = new $_SESSION["language"];
}else{
    if(substr($_SERVER["HTTP_ACCEPT_LANGUAGE"],0,2)=="cs") $lang = new CzechLanguage();
        else $lang = new Language();
}

if (!isset($_SESSION["messages"])) $_SESSION["messages"][0] = new Message($lang->welcome, Message::OK);


$_SESSION["language"] = get_class($lang);

if (isset($_SESSION["user"]) && !$_SESSION["user"]->check()) {
    unset($_SESSION["user"]);
    Message::add(new Message($lang->loginTimeout, Message::WARN));
    $_SESSION["url"] = $_SERVER["REQUEST_URI"];
    header("Location:/login");
}