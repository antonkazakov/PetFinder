package com.greencode.petfinder.ui.pages.petSearchPage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetSearchResultFragment extends Fragment implements PetSearchContract.View {

    @Inject
    PetSearchPresenter petSearchPresenter;

    Map<String, String> filterMap = new HashMap<>();

    public PetSearchResultFragment() {
        // Required empty public constructor
    }

    public void startSearchWithFilters(Map<String, String> filterMap) {
        this.filterMap.putAll(filterMap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_search_result, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        petSearchPresenter.searchPets(filterMap);
    }

    @Override
    public void setPresenter(BasePresenter basePresenter) {

    }

    @Override
    public void showError(String text) {

    }

    @Override
    public void showLoading(boolean isLoading) {

    }

}
