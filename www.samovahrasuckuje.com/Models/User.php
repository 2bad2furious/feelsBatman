<?php

class User
{

    private $id;
    private $name;
    private $registered = false;
    private $password;
    private $email;
    private $admin;
    private $uniqid;
    private $ip;

    public function __construct($email, $password, $name = "", $admin = false)
    {
        $this->email = $email;
        $this->admin = $admin;
        $this->name = $name;
        $this->password = $this->hash($password);
        $this->ip = $_SERVER["REMOTE_ADDR"];

        $querry = $this->validate();
        if ($querry) {
            $this->registered = true;
            $this->id = $querry["id"];
        }
    }

    public function createUser()
    {
        $this->uniqid = uniqid();

        if (!$this->registered) {
            Db::dotaz("INSERT INTO user(username,pw,email,uniqid,admin,description) VALUES(?,?,?,?,?,?)", array($this->name, $this->hash($this->password), $this->email, $this->uniqid, 0, " "));
        }
    }

    private function hash($str)
    {
        return crypt($str, '$2a$07$' . md5($this->uniqid) . '$');
    }

    private function exists()
    {
        return Db::dotazRadek("SELECT id, username, pw, uniqid FROM user WHERE email = ?", array(
            $this->email));
    }

    private function validate()
    {
        $quarry = $this->exists();
        if ($quarry) {
            $this->uniqid = $quarry["uniqid"];
            $this->name = $quarry["username"];
            return hash_equals($quarry["pw"], crypt($this->password, $quarry["pw"])) ? $quarry : false;
        } else
            return false;
    }

    public function getIp()
    {
        return $this->ip;
    }

    public function registered()
    {
        return $this->registered;
    }

    public function id()
    {
        return intval($this->id * 5 * 10000000 * pi());
    }

    public function username()
    {
        return $this->name;
    }

}