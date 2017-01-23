<?php

/**
 * Created by PhpStorm.
 * User: user
 * Date: 17.01.2017
 * Time: 13:10
 */
class CreateMapController extends Controller
{
    public function main()
    {
        if($this->isLogged()) {
            $this->view = "mapView";
        }else{
            $this->forceLogged();
        }
    }
}