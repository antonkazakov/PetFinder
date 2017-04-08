package com.greencode.petfinder.data.repository;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.source.PetDataSource;
import com.greencode.petfinder.data.source.PetSourceFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */
public class PetRepository implements PetDataSource {

    private PetSourceFactory petSourceFactory;

    @Inject
    public PetRepository(PetSourceFactory petSourceFactory) {
        this.petSourceFactory = petSourceFactory;
    }

    @Override
    public Observable<List<Pet>> getPets(String name) {
        return petSourceFactory.create().getPets(name);
    }

    @Override
    public Observable<Pet> getPet(String id) {
        return petSourceFactory.create().getPet(id);
    }

}
