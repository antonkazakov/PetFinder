package com.greencode.petfinder.ui.pages.petSinglePage;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.ui.injection.FragmentScope;

import dagger.Component;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = SinglePetModule.class)
public interface SinglePetComponent {

    void inject(SinglePetFragment singlePetFragment);

}
