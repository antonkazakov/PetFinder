package com.greencode.petfinder.data.sources.shelters;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.data.mappers.ShelterMapper;
import com.greencode.petfinder.data.exception.InvalidLocationException;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */

public class CloudShelterDataSource implements ShelterDataSource {

    private ApiService apiService;
    private ShelterMapper shelterMapper;
    private PetMapper petMapper;
    private Cache<Shelter> shelterCache;

    @Inject
    public CloudShelterDataSource(ApiService apiService, ShelterMapper shelterMapper, Cache<Shelter> shelterCache, PetMapper petMapper) {
        this.apiService = apiService;
        this.shelterMapper = shelterMapper;
        this.shelterCache = shelterCache;
        this.petMapper = petMapper;
    }

    /**
     * 1. Get shelters from server
     * 2. If code 203 which will occure if user is not in NA then replace location pair in map
     * with fake CAlifornia state. Then retry getShelters()
     * 3. Transform all {@link com.greencode.petfinder.data.entity.beans.shelter.ShelterFindResponse}
     * 4. If cache size < 50 put objects into cache
     * 5. Send data to Repository
     *
     * @param map
     * @return
     */
    @Override
    public Observable<List<Shelter>> getShelters(Map<String, String> map) {
        return apiService.findShelter(map)
                .flatMap(shelterFindResponse -> {
                    if (shelterFindResponse.getHeader().getStatus().getCode().equals("203")) {
                        throw new InvalidLocationException(shelterFindResponse.getHeader().getStatus().getMessage());
                    }
                    return Observable.just(shelterFindResponse);
                })
                .retryWhen(observable -> observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (throwable instanceof InvalidLocationException) {
                            map.remove("location");
                            map.put("location", "CA");
                            return apiService.findShelter(map);
                        }
                        return Observable.error(throwable);
                    }
                }))
                .flatMap(shelterFindResponse ->
                        Observable.just(shelterMapper.transform1(shelterFindResponse.getShelters()))
                                .flatMap(shelters -> {
                                    if (shelterFindResponse.getHeader().getStatus().getCode().equals("203")) {
                                        throw new InvalidLocationException(shelterFindResponse.getHeader().getStatus().getMessage());
                                    }
                                    if (shelterCache.getCacheSize() <= 40) {
                                        shelterCache.putAll(shelters);
                                    }
                                    return Observable.just(shelters);
                                }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Shelter> getShelter(Map<String, String> map) {
        return apiService.getShelter(map)
                .flatMap(shelterGetResponse -> {
                    Shelter shelter = shelterMapper.transform(shelterGetResponse);
                    shelterCache.put(shelter);
                    return Observable.just(shelter);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Pet>> getPetsInShelter(Map<String, String> map) {
        return apiService.getSheltersPet(map)
                .flatMap(petFindResponse -> {
                    List<Pet> shelters = petMapper.transform1(petFindResponse.getPets());
                    return Observable.just(shelters);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
