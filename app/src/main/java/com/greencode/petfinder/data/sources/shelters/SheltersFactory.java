package com.greencode.petfinder.data.sources.shelters;

import android.util.Log;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.data.mappers.ShelterMapper;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */

public class SheltersFactory implements AbstractShelterFactory<LocalShelterDataSource, CloudShelterDataSource> {

    private final String TAG = getClass().getSimpleName();

    private ApiService apiService;
    private ShelterMapper shelterMapper;
    private PetMapper petMapper;
    private Cache<Shelter> shelterCache;

    private static LocalShelterDataSource localShelterDataSource;
    private static CloudShelterDataSource cloudShelterDataSource;

    @Inject
    public SheltersFactory(ApiService apiService, Cache<Shelter> shelterCache, ShelterMapper shelterMapper, PetMapper petMapper) {
        this.apiService = apiService;
        this.shelterCache = shelterCache;
        this.shelterMapper = shelterMapper;
        this.petMapper = petMapper;
    }

    @Override
    public ShelterDataSource createDependingOnCache() {
        if (shelterCache.isExpired()){
            return createCloudShelterDataSource();
        }else {
            return createLocalShelterDataSource();
        }
    }

    @Override
    public LocalShelterDataSource createLocalShelterDataSource() {
        if (localShelterDataSource == null){
            localShelterDataSource =  new LocalShelterDataSource(shelterCache);
        }
        Log.i(TAG, "createLocalShelterDataSource: ");
        return localShelterDataSource;
    }

    @Override
    public CloudShelterDataSource createCloudShelterDataSource() {
        if (cloudShelterDataSource == null){
            cloudShelterDataSource = new CloudShelterDataSource(apiService, shelterMapper, shelterCache, petMapper);
        }
        Log.i(TAG, "createCloudShelterDataSource: ");
        return cloudShelterDataSource;
    }
}
