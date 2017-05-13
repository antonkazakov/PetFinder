package com.greencode.petfinder.ui.screens.shelterSingle;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.greencode.petfinder.PFApplication;
import com.greencode.petfinder.R;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.ViewItem;
import com.greencode.petfinder.ui.screens.petList.SinglePetClickListener;
import com.greencode.petfinder.ui.screens.petList.TestFuckItem;
import com.greencode.petfinder.ui.screens.petSingle.SinglePetActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShelterPageFragment extends Fragment implements ShelterPageContract.View, SinglePetClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<ViewItem> viewItems = new ArrayList<>();

    private ShelterPageGodAdapter shelterPageGodAdapter;

    @Inject
    ShelterPresenter shelterPresenter;

    public ShelterPageFragment() {
        // Required empty public constructor
    }

    public static ShelterPageFragment newInstance(Bundle bundle) {
        ShelterPageFragment fragment = new ShelterPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShelterSingleComponent shelterSingleComponent = DaggerShelterSingleComponent.builder()
                .appComponent(PFApplication.getAppComponent())
                .shelterSingleModule(new ShelterSingleModule(this))
                .build();
        shelterSingleComponent.inject(this);

        setRetainInstance(true);
        shelterPageGodAdapter = new ShelterPageGodAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelter_pager, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(shelterPageGodAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String test2 = new StringBuilder()
                .append("https://maps.googleapis.com/maps/api/staticmap?")
                .append("markers=")
                .append("icon:http://i.imgur.com/pSYrJXM.png%7C")
                .append(String.valueOf(bundle.getDouble("latitude")))
                .append(",")
                .append(String.valueOf(bundle.getDouble("longitude")))
                .append("&zoom=17&size=1000x600&key=AIzaSyD4_XFCyfKq2QsR2JA60Ta382RVOtYriU4")
                .toString();
        viewItems.add(new ShelterPageHeaderViewItem(test2));
        viewItems.add(new ShelterPageSectionItem("Contacts", R.color.greenPrimary));
        viewItems.add(new SimpleShelterItemView("Name", bundle.getString("name", "N/A")));
        viewItems.add(new SimpleShelterItemView("Address", bundle.getString("address", "N/A")));
        viewItems.add(new SimpleShelterItemView("Phone", bundle.getString("phone", "N/A")));
        viewItems.add(new SimpleShelterItemView("Email", bundle.getString("email", "N/A")));
        shelterPageGodAdapter.updateData(viewItems);
        shelterPresenter.getSheltersPet(getArguments().getString("id"));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.add("Share");
        menuItem.setIcon(ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_share_white));
    }

    @Override
    public void onShelterLoaded(Shelter shelter) {
        //
    }

    @Override
    public void onPetsFromShelterLoaded(List<TestFuckItem> petList) {
        shelterPageGodAdapter.updateData1(petList);
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

    @Override
    public void onPetClicked(String petId) {
        startActivity(new Intent(getActivity(), SinglePetActivity.class).putExtra("id", petId));
    }
}
