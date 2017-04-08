package com.greencode.petfinder.data;

import android.content.Context;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.location.LocationModule;
import com.greencode.petfinder.data.location.LocationRepository;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.data.repository.PetRepositoryModule;
import com.greencode.petfinder.ui.singlePet.SinglePetFragment;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */
@Singleton
@Component(modules = {NetworkModule.class, PetRepositoryModule.class, AppModule.class, LocationModule.class})
public interface AppComponent {

    ApiService apiService();

    PetRepository petRepository();

    LocationRepository locationRepository();

    Context context();

    Retrofit retrofit();

    void inject(SinglePetFragment singlePetFragment);

}
