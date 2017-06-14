package com.greencode.petfinder.ui.pages.shelterSinglePage;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.BaseView;
import com.greencode.petfinder.ui.pages.petListPage.SinglePetListItemView;

import java.util.List;

/**
 * @author Anton Kazakov
 * @date 23.04.17.
 */

public interface ShelterPageContract {

    interface Presenter extends BasePresenter{

        void getShelter(String id);

        void getSheltersPet(String id);

    }

    interface View extends BaseView{

        void onShelterLoaded(Shelter shelter);

        void onPetsFromShelterLoaded(List<SinglePetListItemView> petList);

    }

}
