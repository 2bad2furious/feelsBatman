<?php
namespace Controllers;

class ErrorController extends Controller {

    public function main() {
        $this->view = "error404";
        header("HTTP/1.0 404 Not Found");
        echo ":/";
    }

}
