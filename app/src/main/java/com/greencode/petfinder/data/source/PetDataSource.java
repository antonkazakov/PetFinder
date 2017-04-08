package com.greencode.petfinder.data.source;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.List;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public interface PetDataSource {

    Observable<List<Pet>> getPets(String name);

    Observable<Pet> getPet(String id);

}
