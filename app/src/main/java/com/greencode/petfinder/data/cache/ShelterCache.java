package com.greencode.petfinder.data.cache;

import com.greencode.petfinder.R;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * @author Anton Kazakov
 * @date 13.04.17.
 */

public class ShelterCache implements Cache<Shelter> {

    private static final long CACHE_EXPIRATION_TIME = 60*60*1000;

    @Inject
    public ShelterCache() {
    }

    @Override
    public void clearCache() {
        Realm realm = Realm.getDefaultInstance();
        realm.where(Shelter.class).findAll().deleteAllFromRealm();
        realm.close();
    }

    @Override
    public Observable<List<Shelter>> getAll() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Shelter.class)
                .findAll()
                .asObservable()
                .map(realm::copyFromRealm)
                .doOnTerminate(realm::close);
    }

    @Override
    public Observable<Shelter> get(String id) {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(Shelter.class)
                .equalTo("id", id)
                .findFirst()
                .asObservable()
                .cast(Shelter.class)
                .map(realm::copyFromRealm)
                .doOnTerminate(realm::close);
    }

    @Override
    public void put(Shelter shelter) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(shelter);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void putAll(List<Shelter> list) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(list);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public boolean isExpired() {
        final Realm realm = Realm.getDefaultInstance();
        if (realm.where(Shelter.class).count() == 0){
            return true;
        }
        realm.close();
        return false;
    }

    @Override
    public long getCacheSize(){
        long count;
        Realm realm = Realm.getDefaultInstance();
        count = realm.where(Shelter.class).count();
        realm.close();
        return count;
    }

}
