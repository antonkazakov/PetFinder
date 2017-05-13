package com.greencode.petfinder.ui.screens.petList;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 26.04.17.
 */

public class SimpleListCategoryItem implements ViewItem {

    private String title;

    private String url;

    public SimpleListCategoryItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int viewType() {
        return R.layout.pet_list_category_item;
    }

}
