<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 08.01.2017
 * Time: 19:26
 */
class RequestManager
{
    public static function sendrequest($url){
        if(!Db::queryOneResult("SELECT * FROM requests WHERE name = ? AND (TIMESTAMPDIFF(MINUTE,date,NOW()) < 20",array($url)))
        $q = json_decode(@file_get_contents($url));
        Db::dotaz("INSERT INTO requests(name,ip,failed) VALUES(?,?,?)",array($url,));
        return $q;
    }
}