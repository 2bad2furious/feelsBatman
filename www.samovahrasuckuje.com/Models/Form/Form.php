<?php

class Form {

    private $name;
    private $submit;
    private $class;
    private $nodes = array();

    public function __construct(String $name, String $submit,String $class = "fancyAf") {
        $this->name = $name;
        $this->submit = $submit;
        $this->class = $class;
    }

    public function addNode(FormNode $node) {
        $this->nodes[] = $node;
    }

    public function returnHtml() {
        $s = "<form class='".$this->class."' method='post' enctype='multipart/form-data'><h2>" . $this->name . "</h2>";
        foreach ($this->nodes as $v) {
            $s.= $v->returnHtml()."<br>";
        }
        $s.= "<input type='submit' value='".$this->submit."'>";
        $s.= "</form>";
        return $s;
    }

    public function hasData() {
        if (empty($_POST)) return false;

        foreach ($this->nodes as $v) {
            // || (!isset($_FILES[$v->getName()]) || !$_FILES[$v->getName()]["error"])
            if (!isset($_POST[$v->getName()])) {
                return false;
            }
        }
        return true;
    }

    public function shouldBeExecuted($data,$file){
        try{
            foreach($data as $k => $v){
                if($v != $_POST[$k]) return true;
            }

            if(!file_exists($file)) return true;

            return (md5_file(($_FILES["file"]["tmp_name"]))!=md5_file($file));

        }catch(Exception $ex){
            return false;
        }
        return false;
    }

    public function nodes(){
        return $this->nodes;
    }

    public function getData() {
        return $_POST;
    }

}
