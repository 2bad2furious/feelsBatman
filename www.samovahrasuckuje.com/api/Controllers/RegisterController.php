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
        $form = UserManager::returnRegisterForm($this->lang);

        if($form->hasData()){
            $data = $form->getData();
            if($data["pw"] != $data["pw"]){
                $msg = $this->lang->registerErrorPasswords;
                $val = false;
            }else {
                $user = new User($data["email"], $data["pw"], $data["name"]);
                $val = $user->createUser();
                if (!$val)
                    $msg = $this->lang->registerFailure;
                else
                    $msg = $this->lang->registerSuccess;
            }
        }else{
            $val = false;
            $msg = $this->lang->formHasNoData;
        }
        echo json_encode(array("value"=>$val,"message"=>$msg));
    }
}