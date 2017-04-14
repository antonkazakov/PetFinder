package com.greencode.petfinder.data.source.shelters;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.mappers.ShelterMapper;
import com.greencode.petfinder.data.source.pet.CloudPetDataSource;
import com.greencode.petfinder.data.source.pet.LocalPetDataSource;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */

public class SheltersFactory implements AbstractShelterFactory<LocalShelterDataSource, CloudShelterDataSource> {

    private ApiService apiService;
    private ShelterMapper shelterMapper;
    private Cache<Shelter> shelterCache;

    private static LocalShelterDataSource localShelterDataSource;
    private static CloudShelterDataSource cloudShelterDataSource;

    @Inject
    public SheltersFactory(ApiService apiService, Cache<Shelter> shelterCache, ShelterMapper shelterMapper) {
        this.apiService = apiService;
        this.shelterCache = shelterCache;
        this.shelterMapper = shelterMapper;
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
        return localShelterDataSource;
    }

    @Override
    public CloudShelterDataSource createCloudShelterDataSource() {
        if (cloudShelterDataSource == null){
            cloudShelterDataSource = new CloudShelterDataSource(apiService, shelterMapper, shelterCache);
        }
        return cloudShelterDataSource;
    }
}
