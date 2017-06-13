package com.greencode.petfinder.ui.screens.petList;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.PFApplication;
import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.ViewItem;
import com.greencode.petfinder.ui.screens.main.MainProgressListener;
import com.greencode.petfinder.ui.screens.petSearch.SearchFiltersActivity;
import com.greencode.petfinder.ui.screens.petSingle.SinglePetActivity;
import com.greencode.petfinder.ui.widgets.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetListFragment extends Fragment implements SinglePetClickListenerExtended,
        PetListContract.View, SwipeRefreshLayout.OnRefreshListener {

    public PetListFragment() {
        // Required empty public constructor
    }

    PetListComponent petListComponent;

    PetListGodAdapter petListGodAdapter;
    List<ViewItem> viewItems = new ArrayList<>();

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView recyclerView;

    @BindView(R.id.empty)
    TextView tvEmpty;

    @Inject
    PetListPresenter petListPresenter;

    MainProgressListener mainProgressListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        petListComponent = DaggerPetListComponent
                .builder()
                .appComponent(PFApplication.getAppComponent())
                .petListModule(new PetListModule(this))
                .build();
        petListComponent.inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainProgressListener = (MainProgressListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainProgressListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_list, container, false);
        ButterKnife.bind(this, view);
        petListGodAdapter = new PetListGodAdapter(this);
        initRecyclerView();
        initRefreshLayout();
        petListPresenter.refreshFeed();
        return view;
    }

    void initRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position){
                    case 2:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setEmptyView(tvEmpty);
        recyclerView.setAdapter(petListGodAdapter);
    }

    void initRefreshLayout(){
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void petsLoaded(List<SinglePetListItemView> petListItemViewList) {
        viewItems.addAll(petListItemViewList);
        petListGodAdapter.updateData(viewItems);
    }

    @Override
    public void onPetsRefreshed(List<SinglePetListItemView> petListItemViewList) {
        viewItems.clear();
        viewItems.addAll(petListItemViewList);
        petListGodAdapter.updateData(viewItems);
    }

    @Override
    public void onLuckyPetAdded(LuckyPetListItemView luckyPetListItemView) {
        viewItems.add(luckyPetListItemView);
        petListGodAdapter.updateData(luckyPetListItemView);
    }

    @Override
    public void onPetsRefreshed1(List<ViewItem> petListItemViewList) {
        petListGodAdapter.updateData(petListItemViewList);
    }

    @Override
    public void setPresenter(BasePresenter basePresenter) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem shareItem = menu.add("Share");
        shareItem.setIcon(R.drawable.ic_action_toolbar_search);
        shareItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        shareItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getActivity(), SearchFiltersActivity.class));
            return true;
        });
    }

    @Override
    public void showError(String text) {
        tvEmpty.setText("Something went wrong :(");
    }

    @Override
    public void showLoading(boolean isLoading) {
        swipeRefreshLayout.setRefreshing(false);
        if (mainProgressListener != null) {
            if (isLoading) {
                mainProgressListener.onStartLoading();
            } else {
                mainProgressListener.onStopLoading();
            }
        }
    }

    @Override
    public void onPetClicked(String id) {

    }

    @Override
    public void onRefresh() {
        petListPresenter.refreshFeed();
    }

    @Override
    public void onPetClicked(String petId, String url) {
        startActivity(new Intent(getActivity(), SinglePetActivity.class)
                .putExtra("url", url)
                .putExtra("id", petId));
    }

}
