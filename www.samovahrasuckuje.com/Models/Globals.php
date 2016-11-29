<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 29.11.2016
 * Time: 13:32
 */
class Globals
{
    public static function parseId($id){
        return ceil($id/50000000/pi());
        //opak User->id();
    }

    public static function errorMessage(){
       return new Message("Something went wrong, please repeat the action or contact our support staff",Message::ERROR);
    }
}