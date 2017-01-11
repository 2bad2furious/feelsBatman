<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 11.01.2017
 * Time: 10:20
 */

$file = $_FILES["file"];
move_uploaded_file($file);