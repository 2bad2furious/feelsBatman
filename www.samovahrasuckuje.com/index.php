<?php
require "startup.php";
$url = explode("/", $_SERVER["REQUEST_URI"]);
array_shift($url);
$l = count($url) - 1;
if (strlen($url[$l]) == 0 && count($url) > 1) {
    unset($url[$l]);
    header("Location:/" . implode("/", $url));
}
$name = $url[0] . "Controller";

if ($name == "Controller")
    $name = "HomeController";

if (file_exists("Controllers/" . $url[0] . "Controller.php"))
    $controller = new $name($url);
else
    $controller = new ErrorController($url);

if (!$controller->isLogged() && $url[0] != "Login") {
    $controller->forceLogged();
}

if($controller->nav()){
    $headerController = new HeaderController($url);
}
if($controller->footer()){
    $footerController = new FooterController($url);
}


echo file_exists("Controllers/" . $url[0] . ".php");
?>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php if (isset($headerController)): ?>
            <header>
                Tohle je header

            </header>
        <?php
        endif;
        $controller->run();
        ?>
    </body>
</html>
