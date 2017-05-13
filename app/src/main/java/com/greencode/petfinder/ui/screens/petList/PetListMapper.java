package com.greencode.petfinder.ui.screens.petList;

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

    public List<TestFuckItem> transformSinglePet1(List<SinglePetListItemView> listItemViews){
        List<TestFuckItem> testFuckItems = new ArrayList<>();
        int j = 0;
        while (j<listItemViews.size() - 2){
            TestFuckItem testFuckItem = new TestFuckItem();
            testFuckItem.setSinglePetListItemView1(listItemViews.get(j));
            testFuckItem.setSinglePetListItemView2(listItemViews.get(j+1));
            testFuckItems.add(testFuckItem);
            j = j+2;
        }
        return testFuckItems;
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
