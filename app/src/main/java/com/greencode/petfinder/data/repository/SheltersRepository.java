package com.greencode.petfinder.data.repository;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.sources.shelters.ShelterDataSource;
import com.greencode.petfinder.data.sources.shelters.SheltersFactory;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 13.04.17.
 */

public class SheltersRepository implements ShelterDataSource {

    private SheltersFactory sheltersFactory;

    @Inject
    public SheltersRepository(SheltersFactory sheltersFactory) {
        this.sheltersFactory = sheltersFactory;
    }

    @Override
    public Observable<List<Shelter>> getShelters(Map<String, String> map) {
        return sheltersFactory.createCloudShelterDataSource().getShelters(map);
    }

    @Override
    public Observable<Shelter> getShelter(Map<String, String> map) {
        return sheltersFactory.createDependingOnCache().getShelter(map);
    }

    @Override
    public Observable<List<Pet>> getPetsInShelter(Map<String, String> map) {
        return sheltersFactory.createCloudShelterDataSource().getPetsInShelter(map);
    }

    public Observable<List<Shelter>> getSheltersFromCloud(Map<String, String> map){
        return sheltersFactory.createCloudShelterDataSource().getShelters(map);
    }

}
