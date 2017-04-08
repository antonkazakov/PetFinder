package com.greencode.petfinder.data.source;

import android.util.Log;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.mappers.PetMapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public class CloudPetDataSource implements PetDataSource {

    private ApiService apiService;
    private PetMapper petMapper;
    private Cache<Pet> petCache;

    @Inject
    public CloudPetDataSource (ApiService apiService, PetMapper petMapper, Cache<Pet> petCache) {
        this.apiService = apiService;
        this.petMapper = petMapper;
        this.petCache = petCache;
    }

    @Override
    public Observable<List<Pet>> getPets(String name) {
        return null;
    }

    @Override
    public Observable<Pet> getPet(String id) {
        return apiService.getPet(id, "77cffd89b0d4cca16a350862872c2261")
                .flatMap(petResponse -> Observable.just(petMapper.transform(petResponse)))
                .doOnNext(pet -> petCache.put(pet))
                .flatMap(Observable::just);
    }

}
