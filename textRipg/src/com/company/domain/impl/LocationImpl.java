package com.company.domain.impl;

import com.company.domain.Item;
import com.company.domain.Location;
import com.company.domain.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public class LocationImpl implements Location {
    private String text;
    private List<Option> Options;

    public LocationImpl(String text) {
        this.text = text;
        this.Options = new ArrayList<Option>();
    }

    @Override
    public List<Option> getOptions() {
        return this.Options;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void addOption(String text, Location targetLocation) {
        this.Options.add(new OptionImpl(text,targetLocation));
    }

    public void addOption(String text, Item item){
        this.Options.add(new OptionImpl(text,item));
    }

    public void removeOption(Option option){
                this.Options.remove(option);
    }
}
