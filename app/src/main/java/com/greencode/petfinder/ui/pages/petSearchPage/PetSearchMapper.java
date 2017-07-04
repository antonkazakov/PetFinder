package com.greencode.petfinder.ui.pages.petSearchPage;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 21.04.17.
 */

public class PetSearchMapper {

    @Inject
    public PetSearchMapper() {
    }

    public PetSearchResultsItemView transformSinglePet(Pet pet) {
        PetSearchResultsItemView petSearchResultsItemView = new PetSearchResultsItemView();
        petSearchResultsItemView.setName(pet.getName());
        return petSearchResultsItemView;
    }

    public List<PetSearchResultsItemView> transformSinglePet(List<Pet> pets) {
        List<PetSearchResultsItemView> singlePetListItemViews = new ArrayList<>(pets.size());
        for (Pet pet : pets) {
            singlePetListItemViews.add(transformSinglePet(pet));
        }
        return singlePetListItemViews;
    }

}
