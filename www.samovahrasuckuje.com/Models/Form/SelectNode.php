<?php

class SelectNode extends FormNode {
    private $options;
    private $label;
    private $name; 
    
    public function __construct($name,$label,array $options = array()){
    $this->name = $name;
    $this->label = $label;
        $this->options = $options; 
    }
    
    public function returnHtml(){
        $s = "<select name='".$this->name."'>";
        foreach($options as $v){
            $s.= $v->returnHtml();
        }
        $s.= "</option>";
    }
}
