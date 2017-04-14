package com.greencode.petfinder.ui.singlePet;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.greencode.petfinder.PFApplication;
import com.greencode.petfinder.R;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.pet.Photo;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class SinglePetFragment extends Fragment implements SinglePetContract.View{

    private SinglePetViewPagerAdapter photoViewPagerAdapter;

    private List<Photo> photos = new ArrayList<>();

    @Inject
    SinglePetPresenter singlePetPresenter;

    SinglePetComponent singlePetComponent;

    public SinglePetFragment() {
    }

    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    CirclePageIndicator mIndicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("test1","onCreateView");
        View view = inflater.inflate(R.layout.fragment_single_pet, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        photoViewPagerAdapter = new SinglePetViewPagerAdapter(getActivity(), photos);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(photoViewPagerAdapter);
        mIndicator = (CirclePageIndicator)view.findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);

        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (verticalOffset == 0){
                new Handler().postDelayed(() -> mIndicator.setVisibility(View.VISIBLE),100);
            }else {
                if (mIndicator.getVisibility() != View.GONE){
                    new Handler().postDelayed(() -> mIndicator.setVisibility(View.GONE),100);
                }
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        singlePetComponent = DaggerSinglePetComponent
                .builder()
                .appComponent(PFApplication.getAppComponent())
                .singlePetModule(new SinglePetModule(this))
                .build();
        singlePetComponent.inject(this);

        singlePetPresenter.loadPet("37612315",false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem shareItem = menu.add("Share");
        shareItem.setIcon(R.drawable.ic_toolbar_options);
        shareItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        shareItem.setOnMenuItemClickListener(item -> {
            Log.i("sfsd", "onMenuItemClick: te");
            return true;
        });

    }

    @Override
    public void showPet(Pet pet) {
        collapsingToolbar.setTitle(pet.getName());
        if (photos.size()>1){
            mIndicator.setVisibility(View.VISIBLE);
        }
        photos.addAll(pet.getPhotos());
        photoViewPagerAdapter.notifyDataSetChanged();
    }


    @Override
    public void setPresenter(BasePresenter basePresenter) {

    }

    @Override
    public void showError(String text) {

    }

    private void showShareDialog(String id){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append("https://www.petfinder.com/petdetail/");
        stringBuilder.append(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "te"));
    }
}
