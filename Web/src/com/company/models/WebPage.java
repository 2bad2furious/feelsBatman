package com.company.models;

/**
 * Created by user on 02.04.2017.
 */
public class WebPage {
    private Presenter presenter;

    private WebPage(){

    }

    public static WebPage create(String[] url){
        //blabla create presenter
        return SomePresenter(url);
    }
}
