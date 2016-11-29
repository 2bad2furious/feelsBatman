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

if ($name == "Controller") $name = "HomeController";

array_shift($url);

if (file_exists("Controllers/" . $name. ".php")) $controller = new $name($url);
else $controller = new ErrorController($url);

if($controller->nav()) $headerController = new HeaderController($url);

if($controller->footer()) $footerController = new FooterController($url);

?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><?= $controller->getTitle(); ?></title>
    </head>
    <body>
        <?php if (isset($headerController)): ?>
            <header>
                <?php $headerController->run();?>
            </header>
        <?php endif;
        $controller->run();
        if (isset($footerController)):?>
    <footer>
        <?php $footerController->run(); ?>
    </footer>
    <?php endif; ?>
    </body>
</html>
<?php var_dump($_SESSION["messages"]);
$_SESSION["messages"] = array();