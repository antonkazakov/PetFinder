package com.greencode.petfinder.data.sources.location;

import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.exception.LocationUnavailableException;

import io.realm.Realm;
import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */

public class LocationLocalDataSource implements LocationSource {

    @Override
    public Observable<SimpleLocation> getMyLocation() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(SimpleLocation.class).count()== 0){
            throw new LocationUnavailableException("CACHE IS EMPTY");
        }
        return realm.where(SimpleLocation.class).findFirst()
                .asObservable().cast(SimpleLocation.class)
                .map(realm::copyFromRealm)
                .doOnTerminate(realm::close);
    }

}
