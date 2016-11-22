<?php

class UserManager {

    public static function returnLoginForm(){
        $loginForm = new Form("Login", "login");
        $loginForm->addNode(new InputNode("name", "Name",null));
        $loginForm->addNode(new InputNode("password", "Password", "password" ));
        return $loginForm;
    }
}