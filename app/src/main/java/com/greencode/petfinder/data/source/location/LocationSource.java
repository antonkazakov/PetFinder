package com.greencode.petfinder.data.source.location;

import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */

public interface LocationSource {

    Observable<SimpleLocation> getMyLocation();

}
