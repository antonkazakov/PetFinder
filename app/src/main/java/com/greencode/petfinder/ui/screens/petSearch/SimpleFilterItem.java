package com.greencode.petfinder.ui.screens.petSearch;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 28.04.17.
 */
// TODO: 28.04.17 REFACTOR
public class SimpleFilterItem implements ViewItem {

    public final static String TYPE_SIZE = "size";
    public final static String TYPE_ANIMAL = "animal";
    public final static String TYPE_SEX = "sex";
    public final static String TYPE_AGE = "age";
    private String title;

    private String value;

    private String type;

    public SimpleFilterItem(String title, String value, String type) {
        this.title = title;
        this.value = value;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int viewType() {
        return R.layout.simple_filter_item;
    }

}
