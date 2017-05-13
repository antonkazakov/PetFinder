package com.greencode.petfinder.ui.screens.petSearch;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.greencode.petfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFiltersActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filters);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle("Search");
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_arrow_back));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SearchFiltersFragment searchFiltersFragment = new SearchFiltersFragment();
        FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_search_filters, searchFiltersFragment);
        transaction.commit();

    }


}
