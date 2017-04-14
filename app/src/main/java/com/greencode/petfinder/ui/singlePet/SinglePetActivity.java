package com.greencode.petfinder.ui.singlePet;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.shelterList.SheltersFragment;

public class SinglePetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SheltersFragment singlePetFragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag("FUCK") == null) {
            singlePetFragment = new SheltersFragment();
            fragmentTransaction.add(R.id.mainContainer, singlePetFragment, "FUCK");
        }
        fragmentTransaction.commit();
    }

}
