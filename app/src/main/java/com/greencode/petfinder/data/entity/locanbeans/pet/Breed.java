package com.greencode.petfinder.data.entity.locanbeans.pet;

import io.realm.RealmObject;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */

public class Breed extends RealmObject{

    private String breed;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
