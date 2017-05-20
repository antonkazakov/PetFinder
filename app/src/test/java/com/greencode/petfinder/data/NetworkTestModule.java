package com.greencode.petfinder.data;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.api.ApiService;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * This module provides all network dependencies like OkHttpClient with interceptors,
 * Retrofit, GSON and RetrofitConverters.
 * <p>
 * Any network dependency should go there.
 *
 * @author Anton Kazakov
 * @date 29.03.17.
 */
@Module
public class NetworkTestModule extends NetworkModule{

    private static String URL;

    public NetworkTestModule(String URL) {
        super(URL);
    }

    @NonNull
    @Singleton
    @Provides
    public  OkHttpClient provideOkHttp1(@NonNull HttpLoggingInterceptor httpLoggingInterceptor,
                                       @NonNull Interceptor keyInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(keyInterceptor)
                .build();
    }

    @NonNull
    @Provides
    @Singleton
    public static Retrofit provideRetrofit1(@NonNull SimpleXmlConverterFactory simpleXmlConverterFactory,
                                           @NonNull RxJavaCallAdapterFactory rxAdapter,
                                           @NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .callbackExecutor(Executors.newFixedThreadPool(5))
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(simpleXmlConverterFactory)
                .build();
    }

    @NonNull
    @Provides
    @Singleton
    public SimpleXmlConverterFactory provideSimpleXmlConverterFactory1() {
        return SimpleXmlConverterFactory.create();
    }

    @NonNull
    @Provides
    @Singleton
    public RxJavaCallAdapterFactory providesRxAdapter1() {
        return RxJavaCallAdapterFactory.create();
    }

    @NonNull
    @Provides
    @Singleton
    public ApiService provideApiService1(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


    @NonNull
    @Provides
    @Singleton
    public Interceptor provideKeyInterceptor1() {
        return chain -> {
            Request request = chain.request();
            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("key", "77cffd89b0d4cca16a350862872c2261")
                    .build();
            request = request.newBuilder().url(httpUrl).build();
            return chain.proceed(request);
        };
    }

    @NonNull
    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor1() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


}
