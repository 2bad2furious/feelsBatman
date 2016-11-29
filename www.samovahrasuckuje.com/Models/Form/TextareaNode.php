<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 29.11.2016
 * Time: 13:22
 */
class TextareaNode extends FormNode
{
    public function __construct(string $name, string $label, $value = "",$endtag= ""){
        $this->name = $name;
        $this->label = $label;
        $this->value = $value;
        $this->endtag = $endtag;
    }

    public function main(){

    }

    public function returnHtml(){
        return "<textarea name='".$this->name."' placeholder='".$this->label."' ".$this->endtag.">".$this->value."</textarea>";
    }
}