package com.greencode.petfinder.data.sources.pets;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.mappers.PetMapper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                .flatMap(Observable::just)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate());
    }

    @Override
    public Observable<List<Pet>> findPet(Map<String, String> requestMap) {
        requestMap.remove("location");
        requestMap.put("location", "AK");
        return apiService.findPet(requestMap)
                .flatMap(petFindResponse -> {
                    List<Pet> pets = petMapper.transform1(petFindResponse.getPets());
                    petCache.putAll(pets);
                    return Observable.just(pets);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Pet> getRandomPet(Map<String, String> requestMap) {
        return apiService.getRandomPet(requestMap)
                .flatMap(petGetResponse -> Observable.just(petMapper.transform(petGetResponse)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Pet>> getSheltersPet(Map<String, String> requestMap) {
        return apiService.getSheltersPet(requestMap)
                .flatMap(petFindResponse -> {
                    List<Pet> pets = petMapper.transform1(petFindResponse.getPets());
                    return Observable.just(pets);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
