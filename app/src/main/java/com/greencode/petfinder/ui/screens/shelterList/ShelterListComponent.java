package com.greencode.petfinder.ui.screens.shelterList;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.ui.FragmentScope;

import dagger.Component;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */
@FragmentScope
@Component(modules = ShelterListModule.class, dependencies = AppComponent.class)
public interface ShelterListComponent {

    void inject(SheltersFragment sheltersFragment);

}
