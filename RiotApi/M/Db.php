<?php

class Db {

    public static $connection;
    public static $prep;

    public static function prep($dotaz){
        self::$prep = self::$connection->prepare($dotaz);
    }
    public static function exe($parameters = array()){
        self::$prep->execute($parameters);

        return self::$prep->fetchAll();
    }
    public static function lastIdOfTable($table,$column){
        $a = Db::queryOneResult("SELECT max(".$column.") as max FROM ".$table);
        if($a && $a["max"]!=null) return $a["max"];
        else return 0;
    }
    public static function connect($host, $dbname, $username, $pw)
    {
        $options = array(
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
            PDO::ATTR_EMULATE_PREPARES => false);

        self::$connection = new PDO("mysql:host=".$host.";dbname=".$dbname, $username, $pw, $options);
    }

    public static function query($dotaz, $parametry = array(), Bool $rowCount = false)
    {
        $pdoQuery = self::$connection->prepare($dotaz);

        $pdoQuery->execute($parametry);

        if($rowCount) return $pdoQuery->rowCount();
        else return !is_bool($pdoQuery->rowCount());

    }
    private static function doQuery($query, $params = array()){
        $pdoQuery = self::$connection->prepare($query);

        $pdoQuery->execute($params);

        return $pdoQuery;
    }
    public static function queryResults($query, $params = array())
    {
        return self::doQuery($query,$params)->fetchAll(PDO::FETCH_ASSOC);
    }

    public static function queryOneResult($query, Array $params = array())
    {
        return self::doQuery($query,$params)->fetch(PDO::FETCH_ASSOC);
    }
    //TODO kdyžtak delete
    public static function lastId() {
        return self::$connection->lastInsertId();
    }
}

?>
