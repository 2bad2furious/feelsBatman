<?php

class LogoutController extends Controller {

    public function main() {
        if(isset($_SESSION["user"]) && is_a($_SESSION["user"],"user")){
        unset($_SESSION["user"]);
        $this->addMessage(new Message($this->lang->logoutSuccess));
        $this->redirect("/Login");
        }else{
        $this->addMessage(new Message($this->lang->logoutFailure));
        }
    }

}
