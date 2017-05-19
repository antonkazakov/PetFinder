package com.greencode.petfinder.ui.screens.petSingle;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.BaseView;
import com.greencode.petfinder.ui.screens.petList.SinglePetListItemView;

import java.util.List;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public interface SinglePetContract {

    interface View extends BaseView {

        void showPet(Pet pet);

        void showNeighbors(List<SinglePetListItemView> pets);

    }

    interface Presenter extends BasePresenter {

        void loadPet(String id, boolean force);

        void loadShelterNeighbor(String shelterId, int limit);

    }

}
