package com.greencode.petfinder.ui.shelterList;

import android.location.Location;
import android.util.Log;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.ui.viewmodels.ShelterListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public class ShelterListMapper {

    @Inject
    public ShelterListMapper() {
    }

    public ShelterListViewModel transform(Shelter shelter, double myLat, double myLong){
        ShelterListViewModel shelterListViewModel = new ShelterListViewModel();
        shelterListViewModel.setId(shelter.getId());
        shelterListViewModel.setName(shelter.getName());
        shelterListViewModel.setAddress(shelter.getAddress());
        shelterListViewModel.setCity(shelter.getCity());
        shelterListViewModel.setState(shelter.getState());
        shelterListViewModel.setZip(shelter.getZip());
        shelterListViewModel.setCountry(shelter.getCountry());
        shelterListViewModel.setPhone(shelter.getPhone());
        shelterListViewModel.setEmail(shelter.getEmail());
        Log.i("TST", myLat + "--" + myLong);
        float distance[] = new float[1];
        Location.distanceBetween(shelter.getLatitude(), shelter.getLongitude(), myLat, myLong, distance);
        shelterListViewModel.setDistanceFromPosition(String.valueOf(distance[0]));
        return shelterListViewModel;
    }

    public List<ShelterListViewModel> transformAll(List<Shelter> shelters, double myLat, double myLong){
        List<ShelterListViewModel> shelterListViewModels = new ArrayList<>();
        for (Shelter shelter : shelters){
            shelterListViewModels.add(transform(shelter, myLat, myLong));
        }
        return shelterListViewModels;
    }

}
