<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 30.11.2016
 * Time: 10:15
 */


class UploadNode extends FormNode
{
    private $accept;
    private $multiple;

    public function __construct($name, $label, $accept="", $multiple=0)
    {
        $this->name = $name;
        $this->label = $label;
        $this->accept = $accept;
        $this->multiple = $multiple;
    }


    public function returnHtml()
    {
        return "<input type='file' name='".$this->name."".(($this->multiple)?"":"")."' accept='".$this->accept."' ".(($this->multiple)?"multiple":"").">";    }
}