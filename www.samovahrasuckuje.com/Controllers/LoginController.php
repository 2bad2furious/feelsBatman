<?php

class LoginController extends Controller{
    public function main() {
        
        unset($_SESSION["messages"]);
        if($this->isLogged()){
            $this->redirect("/");
        }
        //TODO input check

        $form = UserManager::returnLoginForm((isset($_POST["email"]))?$_POST["email"]:"");
        if($form->hasData()){
            $data = $form->getData();
            $user = new User($data["email"],$data["password"]);
                if($user->registered()){
            $_SESSION["user"] = $user;
                $this->redirect("/");
            }else{
                //TODO report login failure
                }
        }
        $this->data["form"] = $form;
        $this->view = "login-form";
        
    }
}
