<?php

class ErrorController extends Controller {

    public function main() {
        header("HTTP/1.0 404 Not Found");
        $this->view = "error404";
    }

}
