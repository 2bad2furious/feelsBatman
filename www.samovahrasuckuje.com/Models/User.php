<?php
class User {

    public $id;
    public $name;
    private $registered = false;
    private $password;
    public $admin;
    private $uniqid;
    private $ip;

    private function hash($str){
        return crypt($str, '$2a$07$' . md5($this->uniqid) . '$');
    }

    private function validate() {
        $quarry = Db::dotazRadek("SELECT user_id, name, password, uniqid FROM user WHERE name = ?", array(
            $this->name
        ));
        
        if($quarry) {
            $this->uniqid = $quarry["uniqid"];
            return hash_equals($quarry["password"], crypt($this->password, $quarry["password"])) ? $quarry : false;
        }
        else
            return false;
    }

       public function __construct($name, $password) {

        $this->name = $name;
        $this->password = $password;
                $this->ip = $_SERVER["REMOTE_ADDR"];
                            
        $querry = Db::dotazRadek("SELECT * FROM user WHERE name = ? and password = ?",array($this->name,$this->password));
        if($querry) {
            $this->registered = true;
            $this->id = $querry["user_id"];
        }
    }


    public function createUser(){

        $this->uniqid = uniqid();

        if(!$this->registered){
           Db::dotaz("INSERT INTO user(name,password,uniqid) VALUES(?,?,?)",array($this->name,$this->password,$this->uniqid));
        }
    }
    
    public function getIp(){
        return $this->ip;
    }
    
    public function registered(){
        return $this->registered;
    }
    
}