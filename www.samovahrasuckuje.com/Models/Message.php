<?php

class Message {
    
    private $type;
    private $text;
    
    const OK = "m-ok";
    const ERROR = "m-error";
    const WARN = "m-warning";

    public function __construct($text, $type = self::OK){
        $this->text = $text; $this->type = $type;
    }

    public function type(){
        return $this->type;
    }

    public function text(){
        return $this->text;
    }

    public static function add(Message $msg){
        $_SESSION["messages"][] = $msg;
    }
    //put your code here
}
