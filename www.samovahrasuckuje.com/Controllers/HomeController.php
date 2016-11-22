<?php

class HomeController extends Controller {

    public function main() {
        $this->data["url"] = $this->parameters; 
        $this->view = "home";
    }

}
