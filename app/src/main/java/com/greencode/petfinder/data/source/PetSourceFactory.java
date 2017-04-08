package com.greencode.petfinder.data.source;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.mappers.PetMapper;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 31.03.17.
 */

public class PetSourceFactory {

    private PetMapper petMapper;
    private ApiService apiService;
    private Cache<Pet> petCache;

    @Inject
    public PetSourceFactory(PetMapper petMapper, ApiService apiService, Cache<Pet> petCache) {
        this.petMapper = petMapper;
        this.apiService = apiService;
        this.petCache = petCache;
    }

    public PetDataSource create(){
        return new CloudPetDataSource(apiService, petMapper, petCache);
    }


}
