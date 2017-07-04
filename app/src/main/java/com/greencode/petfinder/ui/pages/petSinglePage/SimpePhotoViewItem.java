package com.greencode.petfinder.ui.pages.petSinglePage;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 04.07.17.
 */

public class SimpePhotoViewItem implements ViewItem {

    String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public int viewType() {
        return R.layout.square_photo_item2;
    }
}
