package com.greencode.petfinder.ui.screens.shelterSingle;

import android.support.annotation.ColorRes;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 30.04.17.
 */

public class ShelterPageSectionItem implements ViewItem {

    private String value;
    @ColorRes
    private int color;

    public ShelterPageSectionItem(String value, int color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int viewType() {
        return R.layout.section_item;
    }

}
