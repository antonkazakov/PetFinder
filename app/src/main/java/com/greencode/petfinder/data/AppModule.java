package com.greencode.petfinder.data;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Main application module. Provides application context.
 *
 * @author Anton Kazakov
 * @date 06.04.17.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(@NonNull Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    @NonNull
    public Context provideContext(){
        return context;
    }

}
