package com.greencode.petfinder.ui.screens.petList;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.ViewItem;

/**
 * @author Anton Kazakov
 * @date 25.04.17.
 */

public class TestFuckItem implements ViewItem {

    SinglePetListItemView singlePetListItemView1;

    SinglePetListItemView singlePetListItemView2;

    public SinglePetListItemView getSinglePetListItemView1() {
        return singlePetListItemView1;
    }

    public void setSinglePetListItemView1(SinglePetListItemView singlePetListItemView1) {
        this.singlePetListItemView1 = singlePetListItemView1;
    }

    public SinglePetListItemView getSinglePetListItemView2() {
        return singlePetListItemView2;
    }

    public void setSinglePetListItemView2(SinglePetListItemView singlePetListItemView2) {
        this.singlePetListItemView2 = singlePetListItemView2;
    }

    @Override
    public int viewType() {
        return R.layout.pet_list_single_item;
    }

    @Override
    public int hashCode() {
        int result = singlePetListItemView1 != null ? singlePetListItemView1.hashCode() : 0;
        result = 31 * result + (singlePetListItemView2 != null ? singlePetListItemView2.hashCode() : 0);
        return result;
    }
}
