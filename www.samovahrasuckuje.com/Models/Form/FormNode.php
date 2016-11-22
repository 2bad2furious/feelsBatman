<?php

abstract class FormNode{
    protected $name;
    protected $value;
    protected $label;
    protected $endtag;
    
    
    public function __construct(string $name, string $label, string $value = "",$endtag = "") {
    
    $this->name = $name;
    $this->value = $value;
    $this->label = $label;
    $this->endtag = $endtag;
    }
    
    public abstract function returnHtml();   
    
    public function getName(){
       
        return $this->name;
    }
}
