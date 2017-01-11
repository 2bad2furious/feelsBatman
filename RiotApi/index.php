<?php
require "startup.php";

if(!$url[0]){
    $controller = new HomeController($url,$lang);
}else if(class_exists("SummonerController")){
     $controller = new SummonerController($url,$lang);
}else{
    $controller = new ErrorController($url,$lang);
}

$headerController = ($controller->getNav())?new HeaderController($url,$lang): new EmptyController($url, $lang);
$footerController = ($controller->getFooter())?new FooterController($url,$lang): new EmptyController($url, $lang);
?>
<!DOCTYPE html>
<html lang="<?= $lang->getCode()?>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <base href="/">
</head>
<body>
<main>
     <?php
     $headerController->run();
     $controller->run();
     $footerController->run();
     ?>
</main>
</body>
</html>
