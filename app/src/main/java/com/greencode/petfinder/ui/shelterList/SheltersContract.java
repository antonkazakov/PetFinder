package com.greencode.petfinder.ui.shelterList;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.BaseView;
import com.greencode.petfinder.ui.viewmodels.ShelterListViewModel;

import java.util.List;

import io.realm.RealmResults;

/**
 * @author Anton Kazakov
 * @date 02.04.17.
 */

public interface SheltersContract {

    interface View extends BaseView {

        void onSheltersRefreshed(List<ShelterListViewModel> shelterList);

        void onSeltersLoadedMore(List<ShelterListViewModel> shelterList);

    }

    interface Presenter extends BasePresenter {

        void getNearShelters();

        void getNearSheltersWithForceLocationUpdate();

        void getMoreShelters();

    }

}
