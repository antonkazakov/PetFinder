package com.greencode.petfinder.ui.pages.petSearchPage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greencode.petfinder.PFApplication;
import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BasePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetSearchResultFragment extends Fragment implements PetSearchContract.View {

    @Inject
    PetSearchPresenter petSearchPresenter;

    private PetSearchComponent petSearchComponent;
    private Map<String, String> filterMap = new HashMap<>();
    private PetResultsAdapter petResultsAdapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    public PetSearchResultFragment() {
        // Required empty public constructor
    }

    public void startSearchWithFilters(Map<String, String> filterMap) {
        this.filterMap.putAll(filterMap);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        petSearchComponent = DaggerPetSearchComponent
                .builder()
                .appComponent(PFApplication.getAppComponent())
                .petSearchModule(new PetSearchModule(this))
                .build();
        petSearchComponent.inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_search_result, container, false);
        ButterKnife.bind(this,view);
        petResultsAdapter = new PetResultsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(petResultsAdapter);
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

    @Override
    public void showPets(List<PetSearchResultsItemView> petListItemViews) {
        petResultsAdapter.updateData(petListItemViews);
    }
}
