package com.company.domain;

import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public interface Location {
    List<Option> getOptions();
    String getText();
    void addOption(String text, Location targetLocation);
    void addOption(String text, Item targetItem);
    void removeOption(Option option);
}
