package com.greencode.petfinder.data.source;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public interface PetDataSource {

    Observable<Pet> getPet(String id);

    Observable<Pet> findPet(Map<String, String> requestMap);

    Observable<Pet> getRandomPet(Map<String, String> requestMap);

}
