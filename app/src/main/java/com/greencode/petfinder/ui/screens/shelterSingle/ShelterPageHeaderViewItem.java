package com.greencode.petfinder.ui.screens.shelterSingle;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 29.04.17.
 */

public class ShelterPageHeaderViewItem implements ViewItem {

    private String photoUrl;

    public ShelterPageHeaderViewItem(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public int viewType() {
        return R.layout.shelter_page_header_item;
    }
}
