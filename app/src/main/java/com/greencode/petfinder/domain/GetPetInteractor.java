package com.greencode.petfinder.domain;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.domain.base.UseCase;
import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Anton Kazakov
 * @date 20.06.17.
 */

public class GetPetInteractor extends UseCase<Pet, String> {

    private PetRepository petRepository;

    @Inject
    public GetPetInteractor(@UIThread Scheduler uiScheduler,
                            @JobThread Scheduler jobScheduler,
                            PetRepository petRepository) {
        super(uiScheduler, jobScheduler);
        this.petRepository = petRepository;
    }

    @Override
    protected Observable<Pet> buildObservable(String parameter) {
        return petRepository.getPet(parameter);
    }



}
