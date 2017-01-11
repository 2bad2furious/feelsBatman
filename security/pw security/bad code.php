<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 11.01.2017
 * Time: 10:20
 */

$pdo = new PDO("loremipsum");
$username = $_POST["name"];
$pw = $_POST["password"];
$pdo->prepare("INSERT INTO user (user,pw) VALUES(?,?)");
$pdo->exec(array($username,$pw));