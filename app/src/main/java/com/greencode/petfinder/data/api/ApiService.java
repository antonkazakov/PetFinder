package com.greencode.petfinder.data.api;

import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("pet.get")
    Observable<PetGetResponse> getPet(@Field("id") String id, @Field("key") String key);

    @FormUrlEncoded
    @POST("pet.getRandom")
    Observable<PetGetResponse> getRandomPet(@FieldMap Map<String, String> map);

}
