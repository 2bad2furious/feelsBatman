<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 08.01.2017
 * Time: 12:36
 */
class Language
{
    protected static $local;
    protected $code = "en";
    protected static $instance = null;

    public function __construct()
    {
        if (self::$instance == null) {
            self::$instance = $this;
        }

        return self::$instance;
    }

    public static function getLocal($index)
    {
        if (isset(self::$local[$index])) return self::$local[$index];
        else return "";
    }

    public static function setLanguage()
    {
        if (isset($_COOKIE["language"]) && class_exists($_COOKIE["language"]) && is_a(new $_COOKIE["language"], "Languaage")) {
            $val = $_COOKIE["language"];
        } else {
            switch ((substr($_SERVER["HTTP_ACCEPT_LANGUAGE"], 0, 2))) {
                case "cs":
                    $val = "CzechLanguage";
                    break;
                default:
                    $val = "Language";
            }
        }
 
        setcookie("language", $val, time() + (60 * 60 * 24 * 30), "/");

        return new $val();
    }

    public function getCode()
    {
        return $this->code;
    }
}