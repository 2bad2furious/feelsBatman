<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 30.11.2016
 * Time: 13:28
 */
class FileManager
{
    public static function UploadFile($file,$path,$id)
    {
        if (move_uploaded_file($file["tmp_name"], $path . $id . ".png")) {
            return true;
        }
        return false;
    }
}