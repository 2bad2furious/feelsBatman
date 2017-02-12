<?php

class LoginController extends Controller
{
    public function main()
    {
        if ($this->isLogged()) {
            $this->redirect("/");
        }
        //TODO input check
        $form = UserManager::returnLoginForm((isset($_POST["email"])) ? $_POST["email"] : "",$this->lang);
        if ($form->hasData()) {
            $data = $form->getData();
            $user = new User($data["email"], $data["password"]);
            if ($user->registered()) {
                $_SESSION["user"] = $user;
                $this->addMessage(new Message($this->lang->profileLoginSuccess, Message::OK));

                if (isset($_SESSION["url"])){
                    $url = $_SESSION["url"];
                    unset($_SESSION["url"]);
                    $this->redirect($url);
                }
                else $this->redirect("/");

            } else {
                $this->addMessage(new Message($this->lang->profileLoginFailureUsernameOrPassword, Message::ERROR));
            }
        }

        $this->data["form"] = $form;
        $this->view = "login-form";

    }
}
