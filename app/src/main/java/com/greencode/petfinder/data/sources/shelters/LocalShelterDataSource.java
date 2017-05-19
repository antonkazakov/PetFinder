package com.greencode.petfinder.data.sources.shelters;

import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */

public class LocalShelterDataSource implements ShelterDataSource {

    private Cache<Shelter> shelterCache;

    @Inject
    public LocalShelterDataSource(Cache<Shelter> shelterCache) {
        this.shelterCache = shelterCache;
    }

    @Override
    public Observable<List<Shelter>> getShelters(Map<String, String> map) {
        return shelterCache.getAll();
    }

    @Override
    public Observable<Shelter> getShelter(Map<String, String> map) {
        return shelterCache.get(map.get("id"));
    }

}
