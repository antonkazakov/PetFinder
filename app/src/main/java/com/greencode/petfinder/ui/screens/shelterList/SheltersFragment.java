package com.greencode.petfinder.ui.screens.shelterList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.greencode.petfinder.PFApplication;
import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.screens.main.MainContainerActivity;
import com.greencode.petfinder.ui.screens.shelterSingle.ShelterPageActivity;
import com.greencode.petfinder.ui.viewmodels.ShelterListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SheltersFragment extends Fragment implements SheltersContract.View, ShelterClickListener {

    public SheltersFragment() {
    }

    ShelterListComponent shelterListComponent;

    List<ShelterListViewModel> shelterListViewModels = new ArrayList<>();
    ShelterListAdapter shelterListAdapter;
    RecyclerView recyclerView;

    @Inject
    SheltersListPresenter sheltersListPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
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
        shelterListAdapter = new ShelterListAdapter(shelterListViewModels, this);
        initRecyclerView();
        sheltersListPresenter.getNearShelters();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sheltersListPresenter.getNearShelters();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(shelterListAdapter);
    }

    @Override
    public void setPresenter(BasePresenter basePresenter) {

    }

    @Override
    public void showError(String text) {
        Log.i("FUCK1", text);
    }

    @Override
    public void showLoading(boolean isLoading) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem shareItem = menu.add("Search");
        shareItem.setIcon(R.drawable.ic_action_toolbar_search);
        MenuItemCompat.setShowAsAction(shareItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        View searchView = SearchViewCompat.newSearchView(getActivity());
        MenuItemCompat.setActionView(shareItem, searchView);
        SearchViewCompat.setOnQueryTextListener(searchView, new SearchViewCompat.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        MenuItemCompat.setOnActionExpandListener(shareItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                ((MainContainerActivity) getActivity()).hide();
                int id = MenuItemCompat.getActionView(item).getContext()
                        .getResources()
                        .getIdentifier("android:id/search_button", null, null);

                AppCompatImageView editText = (AppCompatImageView) MenuItemCompat.getActionView(item).findViewById(id);
                if (editText.getVisibility() == View.VISIBLE){
                    editText.performClick();
                }
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                ((MainContainerActivity) getActivity()).show();
                return true;
            }
        });
    }

    @Override
    public void onSheltersRefreshed(List<ShelterListViewModel> shelterList) {
        shelterListViewModels.addAll(shelterList);
        shelterListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSeltersLoadedMore(List<ShelterListViewModel> shelterList) {

    }

    @Override
    public void onShelterClicked(int position) {
        Intent intent = new Intent(getActivity(), ShelterPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id",shelterListViewModels.get(position).getId());
        bundle.putDouble("latitude", shelterListViewModels.get(position).getLatitude());
        bundle.putDouble("longitude", shelterListViewModels.get(position).getLongitude());
        bundle.putString("name", shelterListViewModels.get(position).getName());
        bundle.putString("address", shelterListViewModels.get(position).getAddress());
        bundle.putString("phone", shelterListViewModels.get(position).getPhone());
        bundle.putString("email", shelterListViewModels.get(position).getEmail());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
