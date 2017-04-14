package com.greencode.petfinder.data;

import android.content.Context;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.source.location.LocationModule;
import com.greencode.petfinder.data.source.location.LocationRepository;
import com.greencode.petfinder.data.source.pet.PetRepository;
import com.greencode.petfinder.data.source.pet.PetRepositoryModule;
import com.greencode.petfinder.data.source.shelters.ShelterRepositoryModule;
import com.greencode.petfinder.data.source.shelters.SheltersRepository;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */
@Singleton
@Component(modules = {NetworkModule.class, PetRepositoryModule.class, AppModule.class, LocationModule.class, ShelterRepositoryModule.class})
public interface AppComponent {

    ApiService apiService();

    PetRepository petRepository();

    SheltersRepository shelterRepository();

    LocationRepository locationRepository();

    Context context();

    Retrofit retrofit();

}
