package com.greencode.petfinder.domain;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.repository.LocationRepository;
import com.greencode.petfinder.data.repository.SheltersRepository;
import com.greencode.petfinder.domain.qualifiers.JobThread;
import com.greencode.petfinder.domain.qualifiers.UIThread;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Anton Kazakov
 * @date 20.06.17.
 */

public class GetSheltersInteractor extends UseCase<List<Shelter>, Map<String, String>> {

    private LocationRepository locationRepository;
    private SheltersRepository sheltersRepository;

    public GetSheltersInteractor(@UIThread Scheduler uiScheduler,
                                 @JobThread Scheduler jobScheduler,
                                 LocationRepository locationRepository,
                                 SheltersRepository sheltersRepository) {
        super(uiScheduler, jobScheduler);
        this.locationRepository = locationRepository;
        this.sheltersRepository = sheltersRepository;
    }

    @Override
    protected Observable<List<Shelter>> buildObservable(Map<String, String> parameter) {
        return sheltersRepository.getShelters(parameter);
    }
}
