package com.greencode.petfinder.data.source.shelters;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */

public interface ShelterDataSource {

    Observable<List<Shelter>> getShelters(Map<String, String> map);

}
