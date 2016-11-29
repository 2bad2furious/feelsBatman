<?php

class InputNode extends FormNode {
private $type;


    public function __construct(string $name, string $label, $type = "text", $value = "",$endtag= ""){
        $this->name = $name;
        $this->label = $label;
        $this->type = $type;
        $this->value = $value;
        $this->endtag = $endtag;   
        
            }
            
            public function main(){

            }
    
    public function returnHtml(){
     return "<input type='".$this->type."' name='".$this->name."' placeholder='".$this->label."' value='".$this->value."' ".$this->endtag." >";   
    }

}
