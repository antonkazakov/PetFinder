package com.greencode.petfinder.ui.singlePet;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.BaseView;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public interface SinglePetContract {

    interface View extends BaseView {

        void showPet(Pet pet);

    }

    interface Presenter extends BasePresenter {

        void loadPet(String id, boolean force);

    }

}
