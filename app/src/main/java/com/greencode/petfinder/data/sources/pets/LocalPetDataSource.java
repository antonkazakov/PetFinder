package com.greencode.petfinder.data.sources.pets;

import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public class LocalPetDataSource implements PetDataSource {

    private Cache<Pet> cache;

    @Inject
    public LocalPetDataSource(Cache<Pet> cache) {
        this.cache = cache;
    }

    @Override
    public Observable<Pet> getPet(String id) {
        return cache.get(id);
    }

    @Override
    public Observable<List<Pet>> findPet(Map<String, String> requestMap) {
        return cache.getAll();
    }

    @Override
    public Observable<Pet> getRandomPet(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public Observable<List<Pet>> getSheltersPet(Map<String, String> requestMap) {
        return cache.getAll();
    }

}
