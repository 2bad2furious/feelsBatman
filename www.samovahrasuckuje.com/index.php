<?php
require "startup.php";

$url = explode("/", $_SERVER["REQUEST_URI"]);

array_shift($url);

$l = count($url) - 1;

$s = $url[$l];

if(strpos($s,"?")) $url[$l] = explode("?",$s)[0];

if (strlen($url[$l]) == 0 && count($url) > 1) {
    unset($url[$l]);
    header("Location:/" . implode("/", $url));
}
$name = $url[0] . "Controller";

if ($name == "Controller") $name = "HomeController";

array_shift($url);

if (file_exists("Controllers/" . $name. ".php")) $controller = new $name($url,$lang);
else $controller = new ErrorController($url,$lang);

if($controller->nav()) $headerController = new HeaderController($url,$lang);

if($controller->footer()) $footerController = new FooterController($url,$lang);

?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><?= $controller->getTitle(); ?></title>

        <link href="/fonts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="/css/css.css" rel="stylesheet">

        <script src="/js/jquery-3.1.1.min.js" type="application/javascript"></script>
        <script>var msgCount = <?= (isset($_SESSION["messages"]))?count($_SESSION["messages"]):"0"?></script>
        <script src="/js/js.js" type="application/javascript"></script>
    </head>
    <body>
        <?php if (isset($headerController)): ?>
            <header>
                <?php $headerController->run();?>
            </header>
        <?php endif;?>
        <main>
            <div class="messages">
                <?php foreach($_SESSION["messages"] as $v): ?>
                    <span class="<?= ($v->type()==Message::OK)?"message-ok":"message-error" ?>">
                        <?= $v->text(); ?></span>
                <?php endforeach; $_SESSION["messages"] = array(); ?>
            </div>
            <div class="container">
                <?php
                $controller->run();
                ?>
            </div>
            </main>
        <?php if (isset($footerController)):?>
    <footer>
        <?php $footerController->run(); ?>
    </footer>
    <?php endif; ?>
    </body>
</html>