package com.greencode.petfinder.ui.viewmodels.baseModels;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * Simple two(or more) text lines item.
 * primaryText - title (bold)
 * secondaryText - simple text
 * @author Anton Kazakov
 * @date 26.04.17.
 */

public class DoubleTextLineViewItemTemp implements ViewItem {

    private String primaryText;

    private String secondaryText;

    public DoubleTextLineViewItemTemp(String primaryText, String secondaryText) {
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    @Override
    public int viewType() {
        return R.layout.double_line_simple_item;
    }

}
