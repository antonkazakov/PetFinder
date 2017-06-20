package com.greencode.petfinder.ui.pages.petListPage;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 21.04.17.
 */

public class PetListMapper {

    @Inject
    public PetListMapper() {
    }

    public SinglePetListItemView transformSinglePet(Pet pet){
        SinglePetListItemView singlePetListItemView = new SinglePetListItemView();
        singlePetListItemView.setId(pet.getId());
        singlePetListItemView.setName(pet.getName());
        singlePetListItemView.setDescription(pet.getDescription());
        StringBuilder descBuilder = new StringBuilder();
        descBuilder.append(pet.getAnimal());
        descBuilder.append(" ");
        descBuilder.append(pet.getSex());
        descBuilder.append(" ");
        descBuilder.append(pet.getAge());

        singlePetListItemView.setDescription(descBuilder.toString());
        if (pet.getPhotos() != null) {
            singlePetListItemView.setPhotoUrl(pet.getPhotos().get(0).getUrl());
        }
        return singlePetListItemView;
    }

    public List<SinglePetListItemView> transformSinglePet(List<Pet> pets){
        List<SinglePetListItemView> singlePetListItemViews = new ArrayList<>(pets.size());
        for (Pet pet : pets){
            singlePetListItemViews.add(transformSinglePet(pet));
        }
        return singlePetListItemViews;
    }



    public LuckyPetListItemView transformLuckyPet(Pet pet){
        LuckyPetListItemView luckyPetListItemView = new LuckyPetListItemView();
        luckyPetListItemView.setId(pet.getId());
        luckyPetListItemView.setName(pet.getName());
        luckyPetListItemView.setDescription("test");
        luckyPetListItemView.setPhotoUrl(pet.getPhotos().get(0).getUrl());
        return luckyPetListItemView;
    }

    public List<LuckyPetListItemView> transformLuckyPet(List<Pet> pets){
        List<LuckyPetListItemView> luckyPetListItemViews = new ArrayList<>(pets.size());
        for (Pet pet : pets){
            luckyPetListItemViews.add(transformLuckyPet(pet));
        }
        return luckyPetListItemViews;
    }






}
