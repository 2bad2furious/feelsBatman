<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 10.12.2016
 * Time: 18:24
 */
class Language{
    public $loginTimeout = "Your connection has been reset to improve your account's security, please login in again";
    public $insufficientPermissions = "You need to be logged in to be here";
    public $noFormChange = "No change of data detected, if this is nto correct, please contact our support staff";
    public $profileUpdateSuccess = "Profile succesfully edited";
    public $profileUpdateFailureUsername = "Username has to be set!";
    public $generalFailure = "Something went wrong, please repeat the action or contact our support staff";
    public $profileLoginSuccess = "Successfully logged in";
    public $profileLoginFailureUsernameOrPassword = "Wrong email or password";
    public $logoutSuccess = "Successfully logged out";
    public $logoutFailure = "You have to be logged in to log out.";
    public $emailLabel = "Enter your email";
    public $passwordLabel = "Enter your password";
    public $password2Label = "Enter your password again";
    public $usernameLabel = "Enter your username";
    public $registerFormTitle = "Register now!";
    public $registerErrorPasswords = "Passwords don't match";
    public $registerFailure = "Something has gone wrong while registering your account :(";
    public $registerSuccess = "Successfully registered!! :}";
    public $formHasNoData = "Form has no data";
    public $profileDescriptionLabel = "Description of your profile";
    public $profileUploadLabel = "Upload or something";
    public $headerHome = "Home";
    public $headerLogout = "Log the fucking fuck out";
    public $headerRegister = "Register";
    public $headerLogin = "Log In";
    public $userNotFound = "userNotFound";
    public $welcome = "welcome";

    public static function getCurrentLanguage(){
        if(isset($_COOKIE["language"]) && file_exists($_COOKIE["language"]) && class_exists($_COOKIE["language"])){
            $val = $_COOKIE["language"];
        }else{
            $val = self::setLanguage(substr($_SERVER["HTTP_ACCEPT_LANGUAGE"],0,2));
        }
        return new $val;
    }

    public static function setLanguage($lang_code){
        if($lang_code == "cs"){
            $val = "CzechLanguage";
        }else{
            $val = "Language";
        }
        setcookie("language",$val,time() + (60 * 60 * 24 * 30), "/");
        return $val;
    }
}
