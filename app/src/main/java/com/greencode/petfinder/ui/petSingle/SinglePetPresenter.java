package com.greencode.petfinder.ui.petSingle;

import android.util.Log;

import com.greencode.petfinder.data.source.pet.PetRepository;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class SinglePetPresenter implements SinglePetContract.Presenter {

    private PetRepository repository;
    private SinglePetContract.View view;

    @Inject
    public SinglePetPresenter(PetRepository repository, SinglePetContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void loadPet(String id, boolean force) {
        repository.getPet(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pet ->{
                    view.showPet(pet);}
                , throwable ->
                        {
                            view.showError(throwable.getLocalizedMessage());
                        }
                );
    }

    @Override
    public void start() {

    }

}
