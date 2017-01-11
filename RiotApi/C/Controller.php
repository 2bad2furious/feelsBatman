<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 08.01.2017
 * Time: 12:34
 */
abstract class Controller
{
    protected $lang_code;
    public $lang;
    protected $view = "";
    protected $data = array();
    protected $parameters = array();
    protected $title;
    protected $nav = true;
    protected $footer = false;

    public function __construct($url,$lang)
    {
        $this->lang = $lang;
        $this->lang_code = $lang->getCode();
        $this->parameters = $url;
        $this->main();
    }

    protected abstract function main();

    public function run(){
        var_dump($this->view);
        extract($this->data);
        require "V/".$this->view.".php";
    }

    public function getTitle(){
        return (string) $this->title;
    }
    public function getNav(){
        return $this->nav;
    }
    public function getFooter(){
        return $this->footer;
    }

    public static function redirect($url){
     header("location:".$url);
        exit();
    }

    protected function refresh(){
        header("location:".$_SERVER["REQUEST_URI"]);
    }
}