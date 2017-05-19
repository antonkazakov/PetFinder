package com.greencode.petfinder.ui.viewmodels.baseModels;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 19.05.17.
 */

public class FooterViewItem implements ViewItem {

    private String text;

    public FooterViewItem(String text) {
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
        return R.layout.footer_itemview;
    }
}
