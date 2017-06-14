package com.greencode.petfinder.ui.pages.shelterListPage;

import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.BaseView;
import com.greencode.petfinder.ui.viewmodels.ShelterListViewModel;

import java.util.List;

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
