package com.greencode.petfinder.data.entity.locanbeans.breed;

import com.greencode.petfinder.data.entity.locanbeans.pet.Breed;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */

public class AnimalBreed extends RealmObject{

    private String animal;

    private RealmList<Breed> breeds;

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public RealmList<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(RealmList<Breed> breeds) {
        this.breeds = breeds;
    }
}
