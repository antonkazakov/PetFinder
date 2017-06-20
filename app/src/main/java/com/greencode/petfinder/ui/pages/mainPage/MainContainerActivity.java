package com.greencode.petfinder.ui.pages.mainPage;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.pages.petListPage.PetListFragment;
import com.greencode.petfinder.ui.pages.shelterListPage.SheltersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainContainerActivity extends AppCompatActivity{

    @BindView(R.id.bottomNavView)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.logoView)
    AppCompatImageView logoView;

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0, false);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_shelters:
                    viewPager.setCurrentItem(1,true);
                    break;
                case R.id.menu_more:
                    viewPager.setCurrentItem(1,true);
                    break;
            }
            return true;
        });
    }

    public void hide(){
        logoView.setVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void show(){
        logoView.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }


    public static class MainPagerAdapter extends FragmentPagerAdapter{

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return new PetListFragment();
                case 1:
                    return new SheltersFragment();
                case 2:
                    return new SheltersFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }



}
