package com.greencode.petfinder.ui.pages.petListPage;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.ui.FragmentScope;

import dagger.Component;

/**
 * @author Anton Kazakov
 * @date 25.04.17.
 */

@FragmentScope
@Component(modules = PetListModule.class, dependencies = AppComponent.class)
public interface PetListComponent {

    void inject(PetListFragment petListFragment);

}
