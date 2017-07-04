package com.greencode.petfinder.ui.pages.petSinglePage;

import android.util.Log;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.domain.GetPetInteractor;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class SinglePetPresenter implements SinglePetContract.Presenter {

    private static final String TAG = SinglePetPresenter.class.getClass().getSimpleName();

    private PetRepository repository;
    private SinglePetContract.View view;
    private SinglePetMapper singlePetMapper;
    private GetPetInteractor getPetInteractor;


    @Inject
    public SinglePetPresenter(PetRepository repository, SinglePetContract.View view, SinglePetMapper singlePetMapper) {
        this.repository = repository;
        this.view = view;
        this.singlePetMapper = singlePetMapper;
    }

    @Override
    public void loadPet(String id, boolean force) {
        getPetInteractor.execute(id, new Observer<Pet>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
                view.showError(e.getLocalizedMessage());
            }

            @Override
            public void onNext(Pet pet) {
                view.showPet(pet);
            }
        });
    }

    @Override
    public void loadShelterNeighbor(String shelterId, int limit) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", shelterId);
        paramMap.put("count", String.valueOf(limit));
        repository.getSheltersPet(paramMap)
                .flatMap(pets -> Observable.just(singlePetMapper.transformLuckyPetsdf(pets)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(simplePetListItemViews -> {

                });
    }

    @Override
    public void destroy() {

    }

}
