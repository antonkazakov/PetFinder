package com.greencode.petfinder.data.source.shelters;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.cache.ShelterCache;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.beans.shelter.ShelterGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.mappers.Mapper;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.data.mappers.ShelterMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */
@Module
public class ShelterRepositoryModule {

    @Provides
    @Singleton
    public SheltersRepository provideSheltersRepository(@NonNull SheltersFactory sheltersFactory){
        return new SheltersRepository(sheltersFactory);
    }

    @Provides
    @Singleton
    public SheltersFactory provideSheltersFactory(ApiService apiService,
                                                  Cache<Shelter> shelterCache,
                                                  ShelterMapper shelterMapper){
        return new SheltersFactory(apiService, shelterCache, shelterMapper);
    }

    @Singleton
    @Provides
    public Mapper<ShelterGetResponse, Shelter> provideShelterMapper(){
        return new ShelterMapper();
    }

    @Singleton
    @Provides
    public Cache<Shelter> provideShelterCache(){
        return new ShelterCache();
    }


}
