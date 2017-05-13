package com.greencode.petfinder.data.cache;

import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class LocationCache implements Cache<SimpleLocation> {

    private static final long CACHE_EXPIRATION_TIME = 60*60*1000;

    @Inject
    public LocationCache() {
    }

    @Override
    public void clearCache() {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(SimpleLocation.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Observable<List<SimpleLocation>> getAll() {
        //EMPTY
        return null;
    }

    @Override
    public Observable<SimpleLocation> get(String id) {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(SimpleLocation.class)
                .findFirst()
                .asObservable()
                .cast(SimpleLocation.class)
                .map(realm::copyFromRealm)
                .doOnTerminate(realm::close);
    }

    @Override
    public void put(SimpleLocation simpleLocation) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(SimpleLocation.class).findAll().deleteAllFromRealm();
        realm.insert(simpleLocation);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void putAll(List<SimpleLocation> list) {
        //
    }

    @Override
    public boolean isExpired() {
        final Realm realm = Realm.getDefaultInstance();
        if (realm.where(SimpleLocation.class).count() == 0){
            return true;
        }
        SimpleLocation simpleLocation = realm.where(SimpleLocation.class).findFirst();
       /// if (System.currentTimeMillis() - simpleLocation.getCreated() > CACHE_EXPIRATION_TIME){
       //     return true;
       // }
        realm.close();
        return false;
    }

    @Override
    public long getCacheSize() {
        return 0;
    }

}
