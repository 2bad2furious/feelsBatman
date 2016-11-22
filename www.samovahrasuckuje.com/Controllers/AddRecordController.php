<?php

class AddRecordController extends Controller{
    public function main() {
      $form = RecordManager::returnForm();
      $this->data["form"] = $form;
    }

//put your code here
}
