<?php

class HeaderController extends Controller{
    public function main() {
        $this->data["logged"] = $this->isLogged();
        $this->view = "header";
    }

//put your code here
}
