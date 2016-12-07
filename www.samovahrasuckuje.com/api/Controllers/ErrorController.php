<?php
namespace Controllers;

class ErrorController extends Controller {

    public function main() {
        $this->view = "error404";
    }

}
