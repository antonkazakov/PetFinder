package com.greencode.petfinder.data.source;

import android.util.Log;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.mappers.PetMapper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

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
    public Observable<Pet> getPet(String id) {
        return apiService.getPet(id, "77cffd89b0d4cca16a350862872c2261")
                .flatMap(petResponse -> Observable.just(petMapper.transform(petResponse)))
                .doOnNext(pet -> petCache.put(pet))
                .flatMap(Observable::just);
    }

    @Override
    public Observable<Pet> findPet(Map<String, String> requestMap) {
        return apiService.findPet(requestMap)
                .flatMap(petGetResponse -> Observable.just(petMapper.transform(petGetResponse)))
                .doOnNext(pet -> petCache.put(pet))
                .flatMap(Observable::just);

    }

    @Override
    public Observable<Pet> getRandomPet(Map<String, String> requestMap) {
        return apiService.getRandomPet(requestMap)
                .flatMap(petGetResponse -> Observable.just(petMapper.transform(petGetResponse)))
                .doOnNext(pet -> petCache.put(pet))
                .flatMap(Observable::just);
    }

}
