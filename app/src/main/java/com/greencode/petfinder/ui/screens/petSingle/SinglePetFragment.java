package com.greencode.petfinder.ui.screens.petSingle;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.greencode.petfinder.ui.base.ViewItem;
import com.greencode.petfinder.ui.screens.petSingle.viewitems.BigTextViewItem;
import com.greencode.petfinder.ui.screens.shelterSingle.ShelterPageSectionItem;
import com.greencode.petfinder.ui.screens.shelterSingle.SimpleShelterItemView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class SinglePetFragment extends Fragment implements SinglePetContract.View {

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
    SinglePetGodAdapter singlePetGodAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_single_pet, container, false);
        ButterKnife.bind(this, view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);



        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        photos.add(new Photo("1", getArguments().getString("url")));
        photoViewPagerAdapter = new SinglePetViewPagerAdapter(getActivity(), photos);
        viewPager.setAdapter(photoViewPagerAdapter);
        photoViewPagerAdapter.notifyDataSetChanged();
        mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);


        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (verticalOffset == 0) {
                new Handler().postDelayed(() -> mIndicator.setVisibility(View.VISIBLE), 100);
            } else {
                if (mIndicator.getVisibility() != View.GONE) {
                    new Handler().postDelayed(() -> mIndicator.setVisibility(View.GONE), 100);
                }
            }
        });
        setHasOptionsMenu(true);
        singlePetGodAdapter = new SinglePetGodAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(singlePetGodAdapter);
        return view;
    }

    public static SinglePetFragment newInstance(String id, String url) {
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("url", url);
        SinglePetFragment fragment = new SinglePetFragment();
        fragment.setArguments(args);
        return fragment;
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

        singlePetPresenter.loadPet(getArguments().getString("id"), false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem shareItem = menu.add("Share");
        shareItem.setIcon(R.drawable.ic_toolbar_options);
        shareItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        shareItem.setOnMenuItemClickListener(item -> {
            return true;
        });
    }

    @Override
    public void showPet(Pet pet) {
        collapsingToolbar.setTitle(pet.getName());
        if (photos.size() > 1) {
            mIndicator.setVisibility(View.VISIBLE);
        }
        photos.clear();
        photos.addAll(pet.getPhotos());
        photoViewPagerAdapter.notifyDataSetChanged();
        List<ViewItem> viewItems = new ArrayList<>();
        viewItems.add(new SimpleShelterItemView("Name", pet.getName()));
        viewItems.add(new SimpleShelterItemView("Age", pet.getAge()));
        viewItems.add(new SimpleShelterItemView("Sex", pet.getSex()));
        viewItems.add(new ShelterPageSectionItem("About", R.color.colorPrimary));
        viewItems.add(new BigTextViewItem(pet.getDescription()));
        viewItems.add(new ShelterPageSectionItem("Neighbors", R.color.colorPrimary));
        singlePetGodAdapter.updateData(viewItems);
    }

    @Override
    public void showNeighbors(List<SimplePetListItemView> pets) {

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

    private void showShareDialog(String id) {
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
