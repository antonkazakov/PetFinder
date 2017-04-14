package com.greencode.petfinder.data.api;

import com.greencode.petfinder.data.entity.beans.shelter.ShelterFindResponse;
import com.greencode.petfinder.data.entity.beans.pet.PetFindResponse;
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

    /**
     * Returns a record for a single pet.
     * @param id
     * @param key
     * @return
     */
    @FormUrlEncoded
    @POST("pet.get")
    Observable<PetGetResponse> getPet(@Field("id") String id, @Field("key") String key);

    /**
     * Returns a record for a randomly selected pet. You can choose the characteristics of the pet
     * you want returned using the various arguments to this method.This method can return pet records
     * in three formats:
     *      id: just the pet ID
     *      basic: essential information like name, animal, breed, shelter ID, primary photo
     *      full: the complete pet record
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("pet.getRandom")
    Observable<PetGetResponse> getRandomPet(@FieldMap Map<String, String> map);

    /**
     * Searches for pets according to the criteria you provde and returns a collection of pet records
     * matching your search. The results will contain at most count records per query, and a lastOffset tag.
     * To retrieve the next result set, use the lastOffset value as the offset to the next pet.find call.
     * NOTE: the total number of records you are allowed to request may vary depending on the type
     * of developer key you have.
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("pet.find")
    Observable<PetFindResponse> findPet(@FieldMap Map<String, String> map);

    /**
     * Returns a list of IDs or collection of pet records for an individual shelter
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("shelter.getPets")
    Observable<PetFindResponse> getSheltersPet(@FieldMap Map<String, String> map);

    /**
     * Returns a collection of shelter records matching your search criteria.
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("shelter.find")
    Observable<ShelterFindResponse> findShelter(@FieldMap Map<String, String> map);

    /**
     * Returns a list of shelter IDs listing animals of a particular breed.
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("shelter.listByBreed")
    Observable<ShelterFindResponse> breedsInShelter(@FieldMap Map<String, String> map);

}
