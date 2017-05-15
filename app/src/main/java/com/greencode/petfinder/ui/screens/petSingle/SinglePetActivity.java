package com.greencode.petfinder.ui.screens.petSingle;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.greencode.petfinder.R;

public class SinglePetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_arrow_back_white));

        SinglePetFragment singlePetFragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag("FUCK") == null) {
            singlePetFragment = SinglePetFragment.newInstance(getIntent().getStringExtra("id"),
                    getIntent().getStringExtra("url"));
            fragmentTransaction.add(R.id.mainContainer, singlePetFragment, "FUCK");
        }
        fragmentTransaction.commit();


    }

}
