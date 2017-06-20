package com.greencode.petfinder.ui.pages.petSearchPage;

import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.BaseView;

import java.util.Map;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

public interface PetSearchContract {

    interface Presenter extends BasePresenter {

        void searchPets(Map<String, String> filterMap);

    }

    interface View extends BaseView {

        void showPets();

    }

}
