package com.greencode.petfinder.domain;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Anton Kazakov
 * @date 19.06.17.
 */

public class GetPetsInteractor extends UseCase<List<Pet>, Map<String, String>> {

    private PetRepository petRepository;

    @Inject
    public GetPetsInteractor(@NonNull PetRepository petRepository,
                             @UIThread Scheduler uiScheduler,
                             @JobThread Scheduler jobScheduler) {
        super(uiScheduler, jobScheduler);
        this.petRepository = petRepository;
    }

    @Override
    protected Observable<List<Pet>> buildObservable(Map<String, String> parameter) {
        return petRepository.findPet(parameter);
    }

}
