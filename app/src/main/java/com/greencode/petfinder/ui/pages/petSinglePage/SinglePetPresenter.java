package com.greencode.petfinder.ui.pages.petSinglePage;

import android.util.Log;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.domain.GetPetInteractor;
import com.greencode.petfinder.domain.GetPetsInShelterInteractor;

import java.util.HashMap;
import java.util.List;
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

    private static final String TAG = SinglePetPresenter.class.getSimpleName();

    private SinglePetContract.View view;
    private SinglePetMapper singlePetMapper;
    private GetPetInteractor getPetInteractor;
    private GetPetsInShelterInteractor getPetsInShelterInteractor;

    @Inject
    public SinglePetPresenter(SinglePetContract.View view,
                              SinglePetMapper singlePetMapper,
                              GetPetInteractor getPetInteractor,
                              GetPetsInShelterInteractor getPetsInShelterInteractor) {
        this.view = view;
        this.singlePetMapper = singlePetMapper;
        this.getPetInteractor = getPetInteractor;
        this.getPetsInShelterInteractor = getPetsInShelterInteractor;
    }

    @Override
    public void loadPet(String id, boolean force) {
        getPetInteractor.buildAndExecute(id, new Observer<Pet>() {
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
                Log.d(TAG, pet.toString());
                view.showPet(pet);
            }
        });
    }

    @Override
    public void loadShelterNeighbor(String shelterId, int limit) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", shelterId);
        paramMap.put("count", String.valueOf(limit));
        getPetsInShelterInteractor.buildAndNoExecute(paramMap)
                .flatMap(pets -> Observable.just(singlePetMapper.transformLuckyPetsdf(pets)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SimplePetListItemView>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        view.showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<SimplePetListItemView> simplePetListItemViews) {
                        Log.d(TAG, simplePetListItemViews.toString());
                        view.showNeighbors(simplePetListItemViews);
                    }
                });
    }

    @Override
    public void destroy() {
        getPetInteractor.clear();
        getPetsInShelterInteractor.clear();
    }

}
