package com.greencode.petfinder.ui.screens.petList;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 25.04.17.
 */

public class LuckyPetListItemView implements ViewItem {

    private String id;

    private String name;

    private String photoUrl;

    private String description;

    @Override
    public int viewType() {
        return R.layout.luckypet_list_item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
