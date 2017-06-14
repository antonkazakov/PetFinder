package com.greencode.petfinder.ui.pages.shelterSinglePage;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BaseActivity;

import butterknife.BindView;

public class ShelterPageActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        getSupportActionBar().setTitle(bundle.getString("name"));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_shelter_page, ShelterPageFragment.newInstance(bundle));
        fragmentTransaction.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_shelter_page;
    }

}
