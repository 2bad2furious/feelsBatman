<?php
ini_set("session.cookie_httpsonly",1);

function autoloader ($class)
{
    if(file_exists('Models/'.$class.'.php')) require 'Models/'.$class.'.php';
    else if(file_exists('Models/form/'.$class.'.php')) require 'Models/form/'.$class.'.php';
    else if(file_exists('Models/system/'.$class.'.php')) require 'Models/system/'.$class.'.php';
    else if(file_exists("Controllers/" . $class . ".php")) require "Controllers/" . $class . ".php";
    else if(file_exists("Controllers/system/" . $class . ".php")) require "Controllers/system/" . $class . ".php";
    else if(file_exists("Controllers/admin/" . $class . ".php")) require "Controllers/admin/" . $class . ".php";
}
spl_autoload_register("autoloader");
try{
 Db::pripoj('localhost', 'dochazka', 'root', '');
} catch(PDOException $e) {
    die("Failed connecting to DB");
}
session_start();
mb_internal_encoding('UTF-8');
