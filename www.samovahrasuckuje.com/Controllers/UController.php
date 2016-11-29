<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 29.11.2016
 * Time: 11:04
 */

class UController extends Controller
{
    public function main()
    {
        $user = UserManager::exists($this->parameters[0]);
        if($user){
            $this->view = "userInfo";
            $this->data["data"] = $user;
        }else {
            $this->view = "userNotFound";
        }
    }
}