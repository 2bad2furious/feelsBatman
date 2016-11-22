<?php
ini_set("session.cookie_httpsonly",1);

spl_autoload_register(function($class)
{
    if(file_exists('Models/'.$class.'.php')) require 'Models/'.$class.'.php';
    else if(file_exists('Models/form/'.$class.'.php')) require 'Models/form/'.$class.'.php';
    else if(file_exists('Models/system/'.$class.'.php')) require 'Models/system/'.$class.'.php';
    else if(file_exists("Controllers/" . $class . ".php")) require "Controllers/" . $class . ".php";
    else if(file_exists("Controllers/system/" . $class . ".php")) require "Controllers/system/" . $class . ".php";
    else if(file_exists("Controllers/admin/" . $class . ".php")) require "Controllers/admin/" . $class . ".php";
});
try{
 Db::pripoj('localhost', 'dbname', 'user', 'pw');
} catch(PDOException $e) {
    die("Failed connecting to DB");
}
session_start();
mb_internal_encoding('UTF-8');
