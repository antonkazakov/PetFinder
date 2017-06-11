package com.greencode.petfinder.ui.screens.petSingle;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BaseActivity;

public class SinglePetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SinglePetFragment singlePetFragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag("FUCK") == null) {
            singlePetFragment = SinglePetFragment.newInstance(getIntent().getStringExtra("id"),
                    getIntent().getStringExtra("url"));
            fragmentTransaction.add(R.id.mainContainer, singlePetFragment, "FUCK");
        }
        fragmentTransaction.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_single_pet;
    }

}
