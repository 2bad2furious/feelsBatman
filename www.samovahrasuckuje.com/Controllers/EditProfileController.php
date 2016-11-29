<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 29.11.2016
 * Time: 11:34
 */


class EditProfileController extends Controller
{
    public function main()
    {
        if($this->isLogged()){
            $form = UserManager::returnProfileEditorForm();
            $this->view = "editProfile";
            if($form->hasData()){
                $r = UserManager::updateProfile($form->getData());
                if($r) $this->addMessage(new Message("Profile succesfully edited"));
                else $this->addMessage(Globals::errorMessage());
                $form = UserManager::returnProfileEditorForm();
            }
            $this->data["form"] = $form;
        }else{
            $this->addMessage(new Message("You have to be logged in to edit your profile",Message::ERROR));
            $this->redirect("/Login");
        }
        // TODO: Implement main() method.
    }
}