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
            $form = UserManager::returnProfileEditorForm($data,$this->lang);
            $this->view = "editProfile";
            if ($form->hasData()) {
                if (!$form->shouldBeExecuted($data, "upload/prof_pics/" . Globals::parseId($_SESSION["user"]->id())))
                    $this->addMessage(new Message($this->lang->noFormChange,Message::WARN));
                else {
                    $r = UserManager::updateProfile($form->getData(),$_FILES);
                    if($r[0] == $r[1]  && $r[0]== true) $this->addMessage(new Message($this->lang->profileUpdateSuccess));
                    else{
                        if(!$r[0]) $this->addMessage(new Message($this->lang->profileUpdateFailureUsername));
                        if(!$r[1]) $this->addMessage(Globals::errorMessage());
                     }
                    $form = UserManager::returnProfileEditorForm($data,$this->lang);
                }
            }

            $this->data["form"] = $form;
        } else {
            $this->addMessage(new Message($this->lang->insufficientPermissions, Message::ERROR));
            $this->redirect("/Login");
        }
    }
}