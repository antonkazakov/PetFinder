package com.greencode.petfinder.ui.pages.shelterListPage;

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

    public ShelterListViewModel transform(Shelter shelter){
        ShelterListViewModel shelterListViewModel = new ShelterListViewModel();
        shelterListViewModel.setId(shelter.getId());
        shelterListViewModel.setName(shelter.getName());

        StringBuilder address = new StringBuilder();
        address.append(shelter.getAddress());
        address.append(" ");
        address.append(shelter.getCity());
        address.append(", ");
        address.append(shelter.getState());
        address.append(" ");
        address.append(shelter.getCountry());

        shelterListViewModel.setAddress(address.toString());
        shelterListViewModel.setCity(shelter.getCity());
        shelterListViewModel.setState(shelter.getState());
        shelterListViewModel.setZip(shelter.getZip());
        shelterListViewModel.setCountry(shelter.getCountry());
        shelterListViewModel.setPhone(shelter.getPhone());
        shelterListViewModel.setEmail(shelter.getEmail());
        shelterListViewModel.setLatitude(shelter.getLatitude());
        shelterListViewModel.setLongitude(shelter.getLongitude());
        return shelterListViewModel;
    }

    public List<ShelterListViewModel> transformAll(List<Shelter> shelters){
        List<ShelterListViewModel> shelterListViewModels = new ArrayList<>();
        for (Shelter shelter : shelters){
            shelterListViewModels.add(transform(shelter));
        }
        return shelterListViewModels;
    }

}
