package com.greencode.petfinder.data.source.shelters;

import android.util.Log;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.beans.shelter.ShelterFindResponse;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.mappers.ShelterMapper;
import com.greencode.petfinder.data.source.location.InvalidLocationException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */

public class CloudShelterDataSource implements ShelterDataSource {

    private ApiService apiService;
    private ShelterMapper shelterMapper;
    private Cache<Shelter> shelterCache;

    @Inject
    public CloudShelterDataSource(ApiService apiService, ShelterMapper shelterMapper, Cache<Shelter> shelterCache) {
        this.apiService = apiService;
        this.shelterMapper = shelterMapper;
        this.shelterCache = shelterCache;
    }

    /**
     *  A bit tricky call
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
                    if (shelterFindResponse.getHeader().getStatus().getCode().equals("203")){
                        throw new InvalidLocationException(shelterFindResponse.getHeader().getStatus().getMessage());
                    }
                    return Observable.just(shelterFindResponse);
                })
                .retryWhen(observable -> observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (throwable instanceof InvalidLocationException){
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
                            if (shelterFindResponse.getHeader().getStatus().getCode().equals("203")){
                                throw new InvalidLocationException(shelterFindResponse.getHeader().getStatus().getMessage());
                            }
                            if (shelterCache.getCacheSize()<=30){
                                shelterCache.putAll(shelters);
                            }
                            return Observable.just(shelters);
                        }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
