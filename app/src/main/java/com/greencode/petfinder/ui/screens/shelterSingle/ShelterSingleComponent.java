package com.greencode.petfinder.ui.screens.shelterSingle;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.ui.FragmentScope;

import dagger.Component;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */
@FragmentScope
@Component(modules = ShelterSingleModule.class, dependencies = AppComponent.class)
public interface ShelterSingleComponent {

    void inject(ShelterPageFragment shelterPageFragment);

}
