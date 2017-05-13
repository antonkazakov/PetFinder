package com.greencode.petfinder.ui.screens.shelterSingle;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 29.04.17.
 */

public class SimpleShelterItemView implements ViewItem {

    private String name;

    private String value;

    public SimpleShelterItemView(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int viewType() {
        return R.layout.shelter_page_simple_item;
    }
}
