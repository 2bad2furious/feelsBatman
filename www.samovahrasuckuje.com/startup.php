<?php
ini_set("session.cookie_httpsonly", 1);

spl_autoload_register(function ($class) {
    if (file_exists('Models/' . $class . '.php')) require 'Models/' . $class . '.php';
    else if (file_exists('Models/Form/' . $class . '.php')) require_once 'Models/Form/' . $class . '.php';
    else if (file_exists("Controllers/" . $class . ".php")) require_once "Controllers/" . $class . ".php";
    else if (file_exists("Controllers/api/" . $class . ".php")) require_once "Controllers/api/" . $class . ".php";
    });
try {
    //Db::pripoj("localhost","samsgame","root","");
    Db::pripoj('wm73.wedos.net', 'd82268_game', 'w82268_game', 'pv7^8z2{AC');
} catch (PDOException $e) {
    die("Failed connecting to DB");
}
session_start();
mb_internal_encoding('UTF-8');

$name = Language::getCurrentLanguage();
$lang = new $name;

if (!isset($_SESSION["messages"])) $_SESSION["messages"][0] = new Message($lang->welcome, Message::OK);

if (isset($_SESSION["user"]) && !$_SESSION["user"]->check()) {
    unset($_SESSION["user"]);
    Message::add(new Message($lang->loginTimeout, Message::WARN));
    $_SESSION["url"] = $_SERVER["REQUEST_URI"];
    header("Location:/login");
}