package com.greencode.petfinder.data.source.shelters;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;

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

    public Observable<List<Shelter>> getSheltersFromCloud(Map<String, String> map){
        return sheltersFactory.createCloudShelterDataSource().getShelters(map);
    }

}
