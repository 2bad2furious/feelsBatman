<?php

class UserManager {

    public static function returnLoginForm($email=""){
        $loginForm = new Form("Login", "login");
        $loginForm->addNode(new InputNode("email","Enter your email","email",$email,"required"));
        $loginForm->addNode(new InputNode("password", "Enter your password", "password","","required"));
        return $loginForm;
    }

    public static function returnRegisterForm(){
        $registerForm = new Form("Register a new user","Register now!");
        $registerForm->addNode(new InputNode("username","Enter your username","text",(isset($_POST["username"]))?$_POST["username"]:""));
        $registerForm->addNode(new InputNode("pw","Enter your password","password","","required"));
        $registerForm->addNode(new InputNode("pw2","Enter your password once again","password","","required"));
        $registerForm->addNode(new InputNode("email","Enter your email address","email",((isset($_POST["email"]))?$_POST["email"]:""),"required"));
        return $registerForm;
    }

    public static function exists($fakeid){
        return Db::dotazRadek("SELECT username,description FROM user WHERE id=?",array(Globals::parseId($fakeid)));
    }

    public static function returnProfileEditorForm($data){
        $form = new Form("Edit your profile","edit");
        $form->addNode(new InputNode("username","Your username","text",$data["username"],"required"));
        $form->addNode(new TextareaNode("description","Description of your profile",$data["description"]));
        $form->addNode(new UploadNode("file","Upload or something","image",0));
        return $form;
    }

    public static function updateProfile($data,$files){
        if(!strlen($data["username"])) $r0 = false;
        $id = Globals::parseId($_SESSION["user"]->id());
            $r1 = Db::dotazRadek("UPDATE user SET username=?, description=? WHERE id=?",
                array($data["username"],$data["description"],$id));
        if(isset($files["file"]) && !$_FILES["file"]["error"]){
            $img = new Image($files["file"]["tmp_name"]);
            $img2 = new Image($files["file"]["tmp_name"]);
            $img2->resizeToEdge(50);
            $path = "upload/prof_pics/";
            $img2->save($path.$id."_m",IMAGETYPE_PNG,null,1);
            $img->save($path.$id,IMAGETYPE_PNG,null,1);
        }
        return array($r0,$r1);
    }
}