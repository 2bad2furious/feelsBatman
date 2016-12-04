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
        if ($this->isLogged()) {
            $data = Db::dotazRadek("SELECT username,description FROM user WHERE id=?", array(Globals::parseId($_SESSION["user"]->id())));
            $form = UserManager::returnProfileEditorForm($data);
            $this->view = "editProfile";
            if ($form->hasData()) {
                if (!$form->shouldBeExecuted($data, "upload/prof_pics/" . Globals::parseId($_SESSION["user"]->id())))
                    $this->addMessage(Globals::noFormChange());
                else {
                    $r = UserManager::updateProfile($form->getData(),$_FILES);
                    if($r[0] == $r[1]  && $r[0]== true) $this->addMessage(new Message("Profile succesfully edited"));
                    else{
                        if(!$r[0]) $this->addMessage(new Message("Username has to be set!"));
                        if(!$r[1]) $this->addMessage(Globals::errorMessage());
                     }
                    $form = UserManager::returnProfileEditorForm($data);
                }
            }

            $this->data["form"] = $form;
        } else {
            $this->addMessage(new Message("You have to be logged in to edit your profile", Message::ERROR));
            $this->redirect("/Login");
        }
    }
}