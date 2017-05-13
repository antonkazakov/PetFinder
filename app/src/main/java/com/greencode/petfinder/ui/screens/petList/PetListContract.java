package com.greencode.petfinder.ui.screens.petList;

import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.BaseView;
import com.greencode.petfinder.ui.base.ViewItem;

import java.util.List;

/**
 * @author Anton Kazakov
 * @date 20.04.17.
 */

public interface PetListContract {

    interface View extends BaseView{

        void petsLoaded(List<TestFuckItem> petListItemViewList);

        void onPetsRefreshed(List<TestFuckItem> petListItemViewList);

        void onLuckyPetAdded(LuckyPetListItemView luckyPetListItemView);

        void onPetsRefreshed1(List<ViewItem> petListItemViewList);

    }

    interface Presenter extends BasePresenter{

        void refreshFeed();

        void loadMoreFeed();

        void loadLuckyOne();

    }

}
