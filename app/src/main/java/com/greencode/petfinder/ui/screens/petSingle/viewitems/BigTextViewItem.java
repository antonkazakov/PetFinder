package com.greencode.petfinder.ui.screens.petSingle.viewitems;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 30.04.17.
 */

public class BigTextViewItem implements ViewItem {

    private String text;

    public BigTextViewItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int viewType() {
        return R.layout.big_text_item;
    }

}
