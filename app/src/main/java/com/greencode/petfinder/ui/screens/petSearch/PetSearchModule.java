package com.greencode.petfinder.ui.screens.petSearch;

import dagger.Module;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */
@Module
public class PetSearchModule {

    private PetSearchContract.View view;

    public PetSearchModule(PetSearchContract.View view) {
        this.view = view;
    }

}
