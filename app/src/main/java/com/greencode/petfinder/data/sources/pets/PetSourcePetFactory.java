package com.greencode.petfinder.data.sources.pets;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.mappers.PetMapper;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 31.03.17.
 */

public class PetSourcePetFactory implements AbstractPetFactory<LocalPetDataSource, CloudPetDataSource> {

    private PetMapper petMapper;
    private ApiService apiService;
    private Cache<Pet> petCache;

    private static LocalPetDataSource localPetDataSource;
    private static CloudPetDataSource cloudPetDataSource;

    @Inject
    public PetSourcePetFactory(PetMapper petMapper, ApiService apiService, Cache<Pet> petCache) {
        this.petMapper = petMapper;
        this.apiService = apiService;
        this.petCache = petCache;
    }

    @Override
    public PetDataSource createDependingOnCache() {
        if (petCache.isExpired()){
            return createCloudDataSource();
        }else {
            return createCloudDataSource();
        }
    }

    @Override
    public LocalPetDataSource createLocalDataSource() {
        if (localPetDataSource == null){
            localPetDataSource = new LocalPetDataSource(petCache);
        }
        return localPetDataSource;
    }

    @Override
    public CloudPetDataSource createCloudDataSource() {
        if (cloudPetDataSource == null){
            cloudPetDataSource = new CloudPetDataSource(apiService, petMapper, petCache);
        }
        return cloudPetDataSource;
    }
}
