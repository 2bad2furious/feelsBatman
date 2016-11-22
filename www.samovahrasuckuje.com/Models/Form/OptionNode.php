<?php

class OptionNode extends FormNode{
    private $value;
    private $title;
       
    public function __construct($title, $value){
        $this->title = $title;
        $this->value = $value;
    }
    
    public function returnHtml(){
        return "<option value='".$this->value."'>".$this->title."</option>";
    }
}
