package com.greencode.petfinder.data.cache;

import com.google.android.gms.maps.GoogleMap;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * @author Anton Kazakov
 * @date 31.03.17.
 */

public class PetCache implements Cache<Pet> {

    private static final long CACHE_EXPIRATION_TIME = 60*1000;

    @Inject
    public PetCache() {}

    @Override
    public void clearCache() {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Pet.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Observable<List<Pet>> getAll() {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(Pet.class)
                .findAll()
                .asObservable()
                .map(realm::copyFromRealm)
                .doOnTerminate(realm::close);
    }

    @Override
    public Observable<Pet> get(String id) {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(Pet.class)
                .equalTo("id", id)
                .findFirst()
                .asObservable()
                .cast(Pet.class)
                .map(realm::copyFromRealm)
                .doOnTerminate(realm::close);
    }

    @Override
    public void put(Pet pet) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(pet);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void putAll(List<Pet> list) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(list);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public boolean isExpired() {
        final Realm realm = Realm.getDefaultInstance();
        if (realm.where(Pet.class).count() != 0){
            return false;
        }
        return false;
    }

    @Override
    public long getCacheSize() {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(Pet.class).count();
    }

}
