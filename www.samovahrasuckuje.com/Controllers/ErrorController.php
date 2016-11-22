<?php

class ErrorController extends Controller {

    public function main() {
        $this->nav = false;
        $this->footer = false;
        $this->view = "error404";
    }

}
