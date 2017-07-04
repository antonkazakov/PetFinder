package com.greencode.petfinder.ui.pages.petListPage;

import android.util.Log;

import com.greencode.petfinder.data.repository.LocationRepository;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.ui.base.ViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 21.04.17.
 */

public class PetListPresenter implements PetListContract.Presenter {

    private PetRepository petRepository;
    private LocationRepository locationRepository;
    private PetListMapper petListMapper;
    private PetListContract.View view;


    @Inject
    public PetListPresenter(PetRepository petRepository,
                            LocationRepository locationRepository,
                            PetListMapper petListMapper,
                            PetListContract.View view) {
        this.petRepository = petRepository;
        this.locationRepository = locationRepository;
        this.petListMapper = petListMapper;
        this.view = view;
    }

    @Override
    public void refreshFeed() {
        locationRepository.getMyLocation()
                .flatMap(simpleLocation -> {
                    Map<String, String> paramsMap = new HashMap<>();
                    paramsMap.put("location", "AK");
                    paramsMap.put("count", "20");
                    return petRepository.findPet(paramsMap);
                })
                .flatMap(pets -> Observable.just(petListMapper.transformSinglePet(pets)))
                .flatMap(testFuckItems -> {
                    List<ViewItem> viewItems = new ArrayList<>();
                    viewItems.addAll(testFuckItems);
                    return Observable.just(viewItems);
                })
                .doOnTerminate(() -> view.showLoading(false))
                .subscribe(viewItems -> {
                    Log.i("sdfdsfsdf", "refreshFeed:12 ");
                    view.onPetsRefreshed1(viewItems);
                }, throwable -> {
                    Log.e("sdfdsfsdf", "refreshFeed: ",throwable );
                    view.showError(throwable.getLocalizedMessage());
                });

    }

    @Override
    public void loadMoreFeed() {
        locationRepository.getMyLocation()
                .flatMap(simpleLocation -> {
                    Map<String, String> paramsMap = new HashMap<>();
                    paramsMap.put("location", "AK");
                    paramsMap.put("count", "20");
                    return petRepository.findPet(paramsMap);
                })

                .flatMap(pets -> Observable.just(petListMapper.transformSinglePet(pets)))
                .flatMap(testFuckItems -> {
                    List<ViewItem> viewItems = new ArrayList<>();
                    viewItems.addAll(testFuckItems);
                    return Observable.just(viewItems);
                })
                .doOnTerminate(() -> view.showLoading(false))
                .subscribe(viewItems -> {
                    Log.i("sdfdsfsdf", "refreshFeed:12 ");
                    view.onPetsRefreshed1(viewItems);
                }, throwable -> {
                    Log.e("sdfdsfsdf", "refreshFeed: ",throwable );
                    view.showError(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void loadLuckyOne() {
        locationRepository.getMyLocation()
                .flatMap(simpleLocation -> {
                    Map<String, String> paramsMap = new HashMap<>();
                    paramsMap.put("location", "AK");
                    paramsMap.put("output", "full");
                    return petRepository.getRandomPet(paramsMap);
                })
                .flatMap(pet -> Observable.just(petListMapper.transformLuckyPet(pet)))
                .subscribe(luckyPetListItemView -> {
                    view.onLuckyPetAdded(luckyPetListItemView);
                }, throwable -> {
                    Log.e("PetListPresenter", "call: ", throwable);
                    view.showError(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void destroy() {

    }

}
