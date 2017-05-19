package com.greencode.petfinder.ui.screens.petSingle;

import android.util.Log;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.repository.PetRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class SinglePetPresenter implements SinglePetContract.Presenter {

    private PetRepository repository;
    private SinglePetContract.View view;
    private SinglePetMapper singlePetMapper;

    @Inject
    public SinglePetPresenter(PetRepository repository, SinglePetContract.View view, SinglePetMapper singlePetMapper) {
        this.repository = repository;
        this.view = view;
        this.singlePetMapper = singlePetMapper;
    }

    @Override
    public void loadPet(String id, boolean force) {

        repository.getPet(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Pet>() {
                    @Override
                    public void call(Pet pet) {
                        view.showPet(pet);
                    }
                }, throwable -> {
                    Log.e("SINGLE", "call: ",throwable );
                });
    }

    @Override
    public void loadShelterNeighbor(String shelterId, int limit) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", shelterId);
        paramMap.put("count", String.valueOf(limit));
        repository.getSheltersPet(paramMap)
                .flatMap(pets -> Observable.just(singlePetMapper.transformLuckyPetsdf(pets)))
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SimplePetListItemView>>() {
                    @Override
                    public void call(List<SimplePetListItemView> simplePetListItemViews) {

                    }
                });
    }

    @Override
    public void start() {

    }

}
