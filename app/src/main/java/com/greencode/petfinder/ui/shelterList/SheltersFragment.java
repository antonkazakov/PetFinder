package com.greencode.petfinder.ui.shelterList;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greencode.petfinder.PFApplication;
import com.greencode.petfinder.R;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.viewmodels.ShelterListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;


public class SheltersFragment extends Fragment implements SheltersContract.View{

    public SheltersFragment() {}

    ShelterListComponent shelterListComponent;

    List<ShelterListViewModel> shelterListViewModels = new ArrayList<>();
    ShelterListAdapter shelterListAdapter;
    RecyclerView recyclerView;

    @Inject
    SheltersListPresenter sheltersListPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shelterListComponent = DaggerShelterListComponent
                .builder()
                .appComponent(PFApplication.getAppComponent())
                .shelterListModule(new ShelterListModule(this))
                .build();
        shelterListComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sheltes, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        shelterListAdapter = new ShelterListAdapter(shelterListViewModels);
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sheltersListPresenter.getNearShelters();
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(shelterListAdapter);
    }

    @Override
    public void setPresenter(BasePresenter basePresenter) {

    }

    @Override
    public void showError(String text) {

    }

    @Override
    public void onSheltersRefreshed(List<ShelterListViewModel> shelterList) {
        Log.i("dsf", "onSheltersRefreshed: " + shelterList.size());
    }

    @Override
    public void onSeltersLoadedMore(List<ShelterListViewModel> shelterList) {
        Log.i("dsf", "onSheltersRefreshed: " + shelterList.size());
    }

}
