package com.greencode.petfinder.ui.pages.petSinglePage;

import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 26.04.17.
 */

public class SimplePetListItemView implements ViewItem {

    private String id;

    private String photoUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public int viewType() {
        return 0;
    }
}
