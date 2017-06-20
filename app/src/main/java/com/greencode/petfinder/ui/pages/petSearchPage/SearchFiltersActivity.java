package com.greencode.petfinder.ui.pages.petSearchPage;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BaseActivity;

import java.util.Map;

import butterknife.BindView;

public class SearchFiltersActivity extends BaseActivity implements SearchFiltersFragment.OnSearchStartListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setTitle("Search");
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_arrow_back));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SearchFiltersFragment searchFiltersFragment = new SearchFiltersFragment();
        searchFiltersFragment.setSearchListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_search_filters, searchFiltersFragment);
        transaction.addToBackStack("TEST");
        transaction.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_filters;
    }

    @Override
    public void onFiltersAssembled(Map<String, String> filtersMap) {
        PetSearchResultFragment petSearchResultFragment = new PetSearchResultFragment();
        petSearchResultFragment.startSearchWithFilters(filtersMap);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_search_filters, petSearchResultFragment);
        transaction.addToBackStack("TEST");
        transaction.commit();
    }
}
