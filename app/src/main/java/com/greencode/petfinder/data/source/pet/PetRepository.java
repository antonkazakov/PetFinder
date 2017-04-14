package com.greencode.petfinder.data.source.pet;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.source.pet.PetDataSource;
import com.greencode.petfinder.data.source.pet.PetSourcePetFactory;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;


/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */
public class PetRepository implements PetDataSource {

    private PetDataSource petDataSource;

    @Inject
    public PetRepository(PetSourcePetFactory petSourceFactory) {
        this.petDataSource = petSourceFactory.createDependingOnCache();
    }

    @Override
    public Observable<Pet> getPet(String id) {
        return petDataSource.getPet(id);
    }

    @Override
    public Observable<List<Pet>> findPet(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public Observable<Pet> getRandomPet(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public Observable<List<Pet>> getSheltersPet(Map<String, String> requestMap) {
        return null;
    }

}
