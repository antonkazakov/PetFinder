package com.greencode.petfinder.data;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.api.ApiService;

import java.io.IOException;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    public static OkHttpClient provideOkHttp(@NonNull HttpLoggingInterceptor httpLoggingInterceptor,
                                             @NonNull Interceptor keyInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(keyInterceptor)
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
                .callbackExecutor(Executors.newFixedThreadPool(5))
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(simpleXmlConverterFactory)
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
    public Interceptor provideKeyInterceptor(){
        return chain -> {
            Request request = chain.request();
            HttpUrl httpUrl = request.url().newBuilder().addQueryParameter("key", "").build();
            request = request.newBuilder().url(httpUrl).build();
            chain.proceed(request);
            return null;
        };
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
