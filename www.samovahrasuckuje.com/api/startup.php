<?php
ini_set("session.cookie_httpsonly", 1);

spl_autoload_register(function ($class) {
    if(file_exists("/Controllers/".$class.".php")) require $class.".php";
    else if (file_exists('../Models/' . $class . '.php')) require '../Models/' . $class . '.php';
    else if (file_exists('../Models/form/' . $class . '.php')) require '../Models/form/' . $class . '.php';
    else if (file_exists('../Models/system/' . $class . '.php')) require '../Models/system/' . $class . '.php';
    else if (file_exists("../Controllers/" . $class . ".php")) require "../Controllers/" . $class . ".php";
    else if (file_exists("../Controllers/system/" . $class . ".php")) require "../Controllers/system/" . $class . ".php";
    else if (file_exists("../Controllers/admin/" . $class . ".php")) require "../Controllers/admin/" . $class . ".php";
});
try {
    Db::pripoj('wm73.wedos.net', 'd82268_game', 'w82268_game', 'ctXkhHT5');
} catch (PDOException $e) {
    die("Failed connecting to DB");
}
session_start();
mb_internal_encoding('UTF-8');

if (isset($_SESSION["user"]) && !$_SESSION["user"]->check()) {
    unset($_SESSION["user"]);
    Message::add(new Message("Your connection has been reset to improve your account's security, please login in again", Message::WARN));
    $_SESSION["url"] = $_SERVER["REQUEST_URI"];
    header("Location:/login");
}