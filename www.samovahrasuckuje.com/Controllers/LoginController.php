<?php

class LoginController extends Controller{
    public function main() {
        $this->nav =  false;
        $this->footer = false;
        
        unset($_SESSION["messages"]);
        if(isset($_SESSION["user"])){
            $this->redirect("/");
        }
        $form = UserManager::returnLoginForm();
        if($form->hasData()){
            $data = $form->getData();
            $user = new User($data["name"],$data["password"]);
                if($user->registered()){
            $_SESSION["user"] = $user;
                $this->redirect("/");
            }
        }
        $this->data["form"] = $form;
        $this->view = "login-form";
        
    }
}
