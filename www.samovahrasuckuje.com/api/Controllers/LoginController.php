<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 02.01.2017
 * Time: 13:56
 */
class LoginController extends Controller
{

    public function main()
    {
        $form = UserManager::returnLoginForm("", $this->lang);
        if ($form->hasData()) {
            $data = $form->getData();
            $user = new User(null, $data["password"], $data["email"]);
            if ($user - registered()) {
                echo json_encode(array("id"=>$user->id(),"username"=>$user->username()));
            } else {
                echo json_encode(array("id"=>0,"username"=>""));
            }
        }
    }
}