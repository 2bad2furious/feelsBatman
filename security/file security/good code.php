<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 11.01.2017
 * Time: 10:20
 */

$file = $_FILES["file"];
$mime = file_info(finfo_open(FILEINFO_MIME_TYPE),$file);
//TODO check
move_uploaded_file($file);
