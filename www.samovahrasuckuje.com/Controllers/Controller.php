<?php

abstract class Controller {

    protected $title = "";
    protected $view;
    protected $data = array();
    protected $parameters = array();
    protected $nav = true;
    protected $footer = true;
    protected $lang;

    public function __construct(Array $url = array(),Language $lang) {
        $this->parameters = $url;
        $this->lang = $lang;
        $this->main();
    }

    public abstract function main();

    public function run() {
        extract($this->data);
        if (strlen($this->view))
            require("Views/" . $this->view . ".php");
        else {
            if(isset($this->data["form"]))
                require("Views/basic-form.php");
            else
            require ("Views/emptyview.php");
        }
    }

    public function redirect($url) {
        header("Location:" . $url);
        exit();
    }

    protected function addMessage(Message $message) {
        Message::add($message);
    }

    public function hasRights() {
       //isNeeded?
    }

    public function forceLogged() {
        if (!($this->isLogged())) {
            $_SESSION["url"] = $_SERVER["REQUEST_URI"];
            $this->addMessage(new Message($this->lang->insufficientPermission));
            $this->redirect("/Login");
        }
    }

    public function isLogged() {
        if (!empty($_SESSION["user"]))
            return true;

        return false;
    }

    public function footer() {
        return $this->footer;
    }

    public function nav() {
        return $this->nav;
    }
    public function getTitle(){
        return (string)"".$this->title;
    }
}
