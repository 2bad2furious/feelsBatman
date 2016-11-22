<?php

class Message {
    
    public $type;
    public $text;
    
    const OK = "m-ok";
    const ERROR = "m-error";
    
    public function __construct(string $text, $type = self::OK){
        $this->text = $text; $this->type = $type;
    }
    //put your code here
}
