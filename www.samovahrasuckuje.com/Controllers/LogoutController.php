<?php

class LogoutController extends Controller {

    public function main() {
        unset($_SESSION["user"]);
        $this->addMessage(new Message("Úspěšně odhlášen"));
        $this->redirect("/Login");        
    }

}
