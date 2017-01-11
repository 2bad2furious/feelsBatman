<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 02.01.2017
 * Time: 14:05
 */

class RegisterController extends Controller
{

    public function main()
    {
        var_dump($this->lang);
        $form = UserManager::returnRegisterForm($this->lang);
        $msg = "";
        $val = 0;

        if($form->hasData()){
            $data = $form->getData();
            if($data["pw"] != $data["pw"]){
                $msg = $this->lang->registerErrorPasswords;
            }
            $user = new User($data["email"],$data["pw"],$data["name"]);
            $val =  $user->createUser();
        }else{
            $msg = $this->lang->formHasNoData;
        }
        echo json_encode(array("value"=>$val,"message"=>$msg));
    }
}