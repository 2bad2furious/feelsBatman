<?php

class Db {
    
    public static $spojeni;
    public static $prep;

    public static function prep($dotaz){
        return self::$spojeni->prepare($dotaz);
    }
    public static function exe($prep,$parameters = array()){
        $prep->execute($parameters);

        return $prep->fetchAll();
    }
    public static function pripoj($host, $jmenoDatabaze, $jmenoUzivatele, $heslo)
    {       
        $options = array(
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
        PDO::ATTR_EMULATE_PREPARES => false);
        
        self::$spojeni = new PDO("mysql:host=".$host.";dbname=".$jmenoDatabaze, $jmenoUzivatele, $heslo, $options);   
    }
    
    public static function dotaz($dotaz, $parametry = array())
    {
        $dotazPDO = self::$spojeni->prepare($dotaz);

        $dotazPDO->execute($parametry);
        
        return $dotazPDO->rowCount();
    }
    
    public static function dotazRadky($dotaz, $parametry = array())
    {
        
        $dotazPDO = self::$spojeni->prepare($dotaz);
        
        $dotazPDO->execute($parametry);
        
        return $dotazPDO->fetchAll(PDO::FETCH_ASSOC);
    }
    
    public static function dotazRadek($dotaz,Array $parametry = array())
    {
        
        $dotazPDO = self::$spojeni->prepare($dotaz);
        
        $dotazPDO->execute($parametry);
        
        return $dotazPDO->fetch(PDO::FETCH_ASSOC);
    }
    
    public static function posledniId() {
        return self::$spojeni->lastInsertId();
    }
}

?>
