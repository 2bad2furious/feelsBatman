<?php

class HeaderController extends Controller{
    public function main() {
        $this->data["logged"] = $this->isLogged();
        $this->view = "header";
        $this->data["data"] = array(
            "home"=>$this->lang->headerHome,
            "logout"=>$this->lang->headerLogout,
            "login"=>$this->lang->headerLogin,
            "register"=>$this->lang->headerRegister,
            "username"=>(isset($_SESSION["user"]))? $_SESSION["user"]->username() : ""
            );
    }

//put your code here
}
