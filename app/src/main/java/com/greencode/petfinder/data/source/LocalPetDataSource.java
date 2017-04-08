package com.greencode.petfinder.data.source;

import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.List;

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
    public Observable<List<Pet>> getPets(String name) {
        return cache.getAll();
    }

    @Override
    public Observable<Pet> getPet(String id) {
        return cache.get(id);
    }

}
