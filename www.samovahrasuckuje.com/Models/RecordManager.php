<?php

 class RecordManager {

    public static function returnForm($id = 0) {
        if ($id) {
            $str = "Upravit záznam";
            $arr = Db::dotazRadek("SELECT * FROM records WHERE id=?", array($id));
        } else {
            $str = "Přidat záznam";
        }
        $form = new Form($str, "Uložit");
        $form->addNode(new InputNode("recordName", "Název záznamu", "text", ((isset($arr["name"]) ? $arr["name"] : null)), " required"));
        return $form;
    }

}
