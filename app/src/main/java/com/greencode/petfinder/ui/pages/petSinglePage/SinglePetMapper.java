package com.greencode.petfinder.ui.pages.petSinglePage;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 26.04.17.
 */

public class SinglePetMapper {

    @Inject
    public SinglePetMapper() {
    }

    public SimplePetListItemView transformTest(Pet pet) {
        SimplePetListItemView simplePetListItemView = new SimplePetListItemView();
        simplePetListItemView.setId(pet.getId());
        if (pet.getPhotos() != null)
            simplePetListItemView.setPhotoUrl(pet.getPhotos().get(0).getUrl());
        return simplePetListItemView;
    }

    public List<SimplePetListItemView> transformLuckyPetsdf(List<Pet> pets) {
        List<SimplePetListItemView> simplePetListItemViews = new ArrayList<>(pets.size());
        for (Pet pet : pets) {
            simplePetListItemViews.add(transformTest(pet));
        }
        return simplePetListItemViews;
    }

}
