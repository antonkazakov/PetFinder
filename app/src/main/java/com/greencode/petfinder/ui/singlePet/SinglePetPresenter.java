package com.greencode.petfinder.ui.singlePet;

import android.util.Log;

import com.greencode.petfinder.data.repository.PetRepository;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
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
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pet ->{
                                Log.i("sdfsdf", pet.toString());
                    view.showPet(pet);}
                , throwable ->
                        {
                            Log.e("sdfsdf", "sdfs", throwable);
                            view.showError(throwable.getLocalizedMessage());
                        }
                );
    }

    @Override
    public void start() {

    }

}
