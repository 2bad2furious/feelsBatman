<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 08.01.2017
 * Time: 14:39
 */
class Message
{
    private $type;
    private $text;

    const OK = "m-ok";
    const ERROR = "m-error";
    const WARN = "m-warning";

    public function __construct($text, $type = Message::OK)
    {
        $this->text = $text;
        $this->type = $type;
    }

    public function addMessage(Message $message){
        $_SESSION["message"][] = $message;
    }

    public function getText(){
        return $this->text;
    }

    public function getType(){
        return $this->type;
    }
}