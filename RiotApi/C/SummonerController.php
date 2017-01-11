<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 08.01.2017
 * Time: 15:34
 */
class SummonerController extends Controller
{
    protected $region;
    protected $name;
    protected function main()
    {
        $this->name = $this->parameters[1];
        $this->region = DataManager::getRegion($this->parameters[0]);
        $data = DataManager::hasData($this->region,$this->name);
        if(!$data){
            DataManager::getData($this->region,$this->name);
            $this->refresh();
        }else{
            var_dump($data);
        }
    }
}