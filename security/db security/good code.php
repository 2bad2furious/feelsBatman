<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 11.01.2017
 * Time: 10:20
 */

$url = $_GET["page"];


$pdo = new PDO("loremipsum");
$pdo->prepare("SELECT * FROM page WHERE page_id = ?");
$pdo->exec(array($url));