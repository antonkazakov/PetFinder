package com.greencode.petfinder.domain;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.domain.qualifiers.JobThread;
import com.greencode.petfinder.domain.qualifiers.UIThread;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Anton Kazakov
 * @date 19.06.17.
 */

public class GetPets extends UseCase<Pet, String> {

    private PetRepository petRepository;

    public GetPets(@NonNull PetRepository petRepository,
                   @UIThread Scheduler uiScheduler,
                   @JobThread Scheduler jobScheduler) {
        super(uiScheduler, jobScheduler);
        this.petRepository = petRepository;
    }

    @Override
    protected Observable<Pet> buildObservable(String parameter) {
        return petRepository.getPet(parameter);
    }

}
