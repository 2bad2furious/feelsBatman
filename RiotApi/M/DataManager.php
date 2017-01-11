<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 08.01.2017
 * Time: 15:35
 */
class DataManager
{
    private static $key = "RGAPI-62215098-40c6-4966-b793-87e7377027d1";
    public static function getRegion($name){
        $val = "eune";
        switch($name){
            case "eune":{
                $val = "eune";
                break;
            }
            case "euw":{
                $val = "euw";
                break;
            }
            case "na":{
                $val = "na";
                break;
            }
        }
        return $val;
    }

    public static function hasData($region,$name){
        return Db::queryOneResult("SELECT * FROM player WHERE region LIKE ? AND summonername LIKE ?",array($region,$name));
    }

    public static function getData($region,$name){
        $data = RequestManager::sendrequest("https://eune.api.pvp.net/api/lol/".$region."/v1.4/summoner/by-name/".$name."?api_key=".self::$key);
        if($data!=null){
            Db::query("INSERT INTO player(region,summonerid,summonername) VALUES(?,?,?)",array($region,$data->$name->id,mb_strtolower($data->$name->name)));
        }
        }
}