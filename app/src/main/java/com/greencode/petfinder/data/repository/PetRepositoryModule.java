package com.greencode.petfinder.data.repository;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.mappers.Mapper;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.data.sources.pets.PetSourcePetFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 31.03.17.
 */
@Module
public class PetRepositoryModule {

    @Singleton
    @Provides
    public PetRepository providePetRepository(@NonNull PetSourcePetFactory petSourceFactory) {
        return new PetRepository(petSourceFactory);
    }

    @Singleton
    @Provides
    public PetSourcePetFactory providePetSourceFactory(@NonNull ApiService apiService,
                                                       @NonNull PetMapper petMapper,
                                                       @NonNull Cache<Pet> petCache){
        return new PetSourcePetFactory(petMapper, apiService, petCache);
    }

    @Singleton
    @Provides
    public Mapper<PetGetResponse, Pet> providePetMapper(){
        return new PetMapper();
    }

    @Singleton
    @Provides
    public Cache<Pet> providePetCache(){
        return new PetCache();
    }


}
