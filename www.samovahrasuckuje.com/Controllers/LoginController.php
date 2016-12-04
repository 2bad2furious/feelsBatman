<?php

class LoginController extends Controller
{
    public function main()
    {

        if ($this->isLogged()) {
            $this->redirect("/");
        }
        //TODO input check
        $form = UserManager::returnLoginForm((isset($_POST["email"])) ? $_POST["email"] : "");
        if ($form->hasData()) {
            $data = $form->getData();
            $user = new User($data["email"], $data["password"]);
            if ($user->registered()) {
                $_SESSION["user"] = $user;
                $this->addMessage(new Message("Successfully logged in", Message::OK));

                if (isset($_SESSION["url"])) $this->redirect($_SESSION["url"]);
                else $this->redirect("/");

            } else {
                $this->addMessage(new Message("Wrong email or password", Message::ERROR));
                //TODO report login failure
            }
        }

        $this->data["form"] = $form;
        $this->view = "login-form";

    }
}
