<?php

class RegisterController extends Controller
{
    public function main()
    {
        if($this->isLogged()){
            $this->redirect("/");
        }
        $form = UserManager::returnRegisterForm($this->lang);
        $reason = "";
        //TODO ajax? name checking
        //TODO input check
        if($form->hasData()){
            $data = $form->getData();
            $valid = true;
            try{
                if($data["pw"]!=$data["pw2"]) {
                    $reason = $this->lang->registerErrorPasswords;
                    $valid = false;
                }
            $user = new User($data["email"],$data["pw"],$data["username"]);
            $valid = $user->createUser();
            }catch(Exception $ex){
            $valid = false;
                $reason = $this->lang->generalFailure.$ex->getMessage();
            }
            if($valid){
                $this->redirect("/Login");
            }else{
                echo (string)$reason;
            }
        }
        $this->data["form"] = $form;
    }
}