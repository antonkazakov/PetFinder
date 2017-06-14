package com.greencode.petfinder.ui.pages.petSearchPage;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 13.06.2017.
 */

public class PetSearchResultsItemView implements ViewItem {

    private String name;

    private String value;

    public PetSearchResultsItemView(String name, String value) {
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
