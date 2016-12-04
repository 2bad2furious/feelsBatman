<?php

class LogoutController extends Controller {

    public function main() {
        unset($_SESSION["user"]);
        $this->addMessage(new Message("Successfully logged out"));
        $this->redirect("/Login");        
    }

}
