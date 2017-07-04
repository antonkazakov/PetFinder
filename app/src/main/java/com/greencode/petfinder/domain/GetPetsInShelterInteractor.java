package com.greencode.petfinder.domain;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.domain.base.UseCase;
import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Anton Kazakov
 * @date 20.06.17.
 */

public class GetPetsInShelterInteractor extends UseCase<List<Pet>, Map<String, String>> {

    private PetRepository petRepository;

    @Inject
    public GetPetsInShelterInteractor(@UIThread Scheduler uiScheduler,
                                      @JobThread Scheduler jobScheduler,
                                      PetRepository petRepository) {
        super(uiScheduler, jobScheduler);
        this.petRepository = petRepository;
    }

    @Override
    protected Observable<List<Pet>> buildObservable(Map<String, String> parameter) {
        return petRepository.getSheltersPet(parameter);
    }



}
