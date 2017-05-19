package com.greencode.petfinder.data.repository;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.sources.pets.PetDataSource;
import com.greencode.petfinder.data.sources.pets.PetSourcePetFactory;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;


/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */
public class PetRepository implements PetDataSource {

    private PetSourcePetFactory petSourcePetFactory;

    @Inject
    public PetRepository(PetSourcePetFactory petSourcePetFactory) {
        this.petSourcePetFactory = petSourcePetFactory;
    }

    @Override
    public Observable<Pet> getPet(String id) {
        return petSourcePetFactory.createDependingOnCache().getPet(id);
    }

    @Override
    public Observable<List<Pet>> findPet(Map<String, String> requestMap) {
        return petSourcePetFactory.createCloudDataSource().findPet(requestMap);
    }

    @Override
    public Observable<Pet> getRandomPet(Map<String, String> requestMap) {
        return petSourcePetFactory.createCloudDataSource().getRandomPet(requestMap);
    }

    @Override
    public Observable<List<Pet>> getSheltersPet(Map<String, String> requestMap) {
        return petSourcePetFactory.createDependingOnCache().getSheltersPet(requestMap);
    }

}
