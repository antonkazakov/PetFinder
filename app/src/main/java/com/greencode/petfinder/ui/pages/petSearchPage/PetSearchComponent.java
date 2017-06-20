package com.greencode.petfinder.ui.pages.petSearchPage;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.ui.injection.FragmentScope;

import dagger.Component;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

@FragmentScope
@Component(modules = {PetSearchModule.class}, dependencies = {AppComponent.class})
public interface PetSearchComponent {

    void inject(PetSearchResultFragment petSearchResultFragment);

}
