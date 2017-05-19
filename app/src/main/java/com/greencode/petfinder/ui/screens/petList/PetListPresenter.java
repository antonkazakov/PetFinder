package com.greencode.petfinder.ui.screens.petList;

import android.util.Log;

import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.repository.LocationRepository;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.ui.base.ViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

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
//        Observable<SimpleLocation> simpleLocationObservable = locationRepository.getMyLocation();
//
//        Observable<List<TestFuckItem>> petObservable = simpleLocationObservable
//                .flatMap(simpleLocation -> {
//                    Map<String, String> paramsMap = new HashMap<>();
//                    paramsMap.put("key", "77cffd89b0d4cca16a350862872c2261");
//                    paramsMap.put("location", "QC");
//                    paramsMap.put("count", "20");
//                    return petRepository.findPet(paramsMap);
//                })
//                .flatMap(pets -> Observable.just(petListMapper.transformSinglePet(pets)))
//                .flatMap(listItemViews -> Observable.just(petListMapper.transformSinglePet1(listItemViews)));

//        Observable<LuckyPetListItemView> luckyPetListItemViewObservable = simpleLocationObservable
//                .flatMap(simpleLocation -> {
//                    Map<String, String> paramsMap = new HashMap<>();
//                    paramsMap.put("key", "77cffd89b0d4cca16a350862872c2261");
//                    paramsMap.put("location", "QC");
//                    return petRepository.getRandomPet(paramsMap);
//                })
//                .flatMap(pet -> Observable.just(petListMapper.transformLuckyPet(pet)));
//
//        Observable.zip(petObservable, luckyPetListItemViewObservable,(testFuckItems, luckyPetListItemView) -> {
//            List<ViewItem> viewItems = new ArrayList<>();
//            viewItems.addAll(testFuckItems);
//            viewItems.add(luckyPetListItemView);
//            return viewItems;
//        })
//                .doOnSubscribe(() -> view.showLoading(true))
//                .doOnTerminate(() -> view.showLoading(false))
//                .subscribe(list -> {
//                    view.onPetsRefreshed1(list);
//                }, throwable -> {
//                    view.showError("ERROR");
//                });
        //Log.i("dfdsf", "refreshFeed: ");
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
                    paramsMap.put("key", "77cffd89b0d4cca16a350862872c2261");
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
                    paramsMap.put("key", "77cffd89b0d4cca16a350862872c2261");
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
    public void start() {

    }

}
