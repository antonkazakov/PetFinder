package com.greencode.petfinder.domain;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.repository.SheltersRepository;
import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Anton Kazakov
 * @date 20.06.17.
 */

public class GetShelterInteractor extends UseCase<Shelter, Map<String, String>> {

    private SheltersRepository sheltersRepository;

    @Inject
    public GetShelterInteractor(@UIThread Scheduler uiScheduler,
                                @JobThread Scheduler jobScheduler,
                                SheltersRepository sheltersRepository) {
        super(uiScheduler, jobScheduler);
        this.sheltersRepository = sheltersRepository;
    }

    @Override
    protected Observable<Shelter> buildObservable(Map<String, String> parameter) {
        return sheltersRepository.getShelter(parameter);
    }
}
