package com.greencode.petfinder.ui.main;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.shelterList.SheltersFragment;
import com.greencode.petfinder.utils.ActivityUtils;

public class MainContainerActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    ActivityUtils.replaceFragmentToActivity(
                            getSupportFragmentManager(),
                            SheltersFragment.newInstance("", ""),
                            R.id.mainContainer,
                            false);
                    break;
                case R.id.menu_shelters:
                    ActivityUtils.replaceFragmentToActivity(
                            getSupportFragmentManager(),
                            SheltersFragment.newInstance("", ""),
                            R.id.mainContainer,
                            false);
                    break;
            }
            return true;
        });
    }



}
