package com.greencode.petfinder.ui.screens.petList;

/**
 * @author Anton Kazakov
 * @date 25.04.17.
 */

public interface SinglePetClickListenerExtended extends SinglePetClickListener{

    void onPetClicked(String petId, String url);

}
