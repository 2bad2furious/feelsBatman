<?php

class Form {

    private $name;
    private $submit;
    private $nodes = array();

    public function __construct(String $name, String $submit) {
        $this->name = $name;
        $this->submit = $submit;
    }

    public function addNode(FormNode $node) {
        $this->nodes[] = $node;
    }

    public function returnHtml() {
        $s = "<form method='post' enctype='multipart/form-data'><h2>" . $this->name . "</h2>";
        foreach ($this->nodes as $v) {
            $s.= $v->returnHtml();
        }
        $s.= "<input type='submit' value='".$this->submit."'>";
        $s.= "</form>";
        return $s;
    }

    public function hasData() {
        if (empty($_POST))
            return false;
        foreach ($this->nodes as $v) {            
                var_dump($v->main());
                echo "<br>";
            if (!isset($_POST[$v->getName()])) {
//                return false;
            }
        }
        return true;
    }

    public function getData() {
        return $_POST;
    }

}
