package com.greencode.petfinder.data;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 *
 * This module provides all network dependencies like OkHttpClient with interceptors,
 * Retrofit, GSON and RetrofitConverters.
 *
 * Any network dependency should go there.
 *
 * @author Anton Kazakov
 * @date 29.03.17.
 */
@Module
public class NetworkModule {

    @NonNull
    @Singleton
    @Provides
    public static OkHttpClient provideOkHttp(@NonNull HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @NonNull
    @Provides
    @Singleton
    public static Retrofit provideRetrofit(@NonNull SimpleXmlConverterFactory simpleXmlConverterFactory,
                                           @NonNull RxJavaCallAdapterFactory rxAdapter,
                                           @NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("http://api.petfinder.com")
                .client(client)
                .addConverterFactory(simpleXmlConverterFactory)
                .addCallAdapterFactory(rxAdapter)
                .build();
    }

    @NonNull
    @Provides
    @Singleton
    public SimpleXmlConverterFactory provideSimpleXmlConverterFactory() {
        return SimpleXmlConverterFactory.create();
    }

    @NonNull
    @Provides
    @Singleton
    public RxJavaCallAdapterFactory providesRxAdapter() {
        return RxJavaCallAdapterFactory.create();
    }

    @NonNull
    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @NonNull
    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

}
