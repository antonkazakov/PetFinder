package com.greencode.petfinder.ui.singlePet;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greencode.petfinder.PFApplication;
import com.greencode.petfinder.R;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.pet.Photo;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.location.LocationRepository;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 */
public class SinglePetFragment extends Fragment implements SinglePetContract.View{

    private SinglePetViewPagerAdapter photoViewPagerAdapter;

    private ViewPager viewPager;

    private List<Photo> photos = new ArrayList<>();

    @Inject
    PetRepository petRepository;

    @Inject
    LocationRepository locationRepository;

    SinglePetContract.Presenter presenter;

    public SinglePetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_pet, container, false);

        photoViewPagerAdapter = new SinglePetViewPagerAdapter(getActivity(), photos);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(photoViewPagerAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PFApplication.getAppComponent().inject(this);
        presenter = new SinglePetPresenter(petRepository, this);
        presenter.loadPet("37612315",false);
    }

    @Override
    public void showPet(Pet pet) {
        photos.addAll(pet.getPhotos());
        photoViewPagerAdapter.notifyDataSetChanged();
    }


    @Override
    public void setPresenter(BasePresenter basePresenter) {

    }

    @Override
    public void showError(String text) {

    }
}
