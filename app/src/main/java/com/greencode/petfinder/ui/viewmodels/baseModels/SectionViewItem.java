package com.greencode.petfinder.ui.viewmodels.baseModels;

import android.support.annotation.ColorRes;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * Section viewItem used in recyclerviews across the app
 * textValue - textValue of the header text
 * textColor - textColor
 * @author Anton Kazakov
 * @date 30.04.17.
 */

public class SectionViewItem implements ViewItem {

    private String textValue;
    @ColorRes private int textColor;

    public SectionViewItem(String value, int color) {
        this.textValue = value;
        this.textColor = color;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public int viewType() {
        return R.layout.section_item;
    }

}
