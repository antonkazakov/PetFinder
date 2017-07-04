package com.greencode.petfinder.ui.pages.petSinglePage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import com.greencode.petfinder.ui.base.BaseFragment;
import com.greencode.petfinder.ui.base.BasePresenter;
import com.greencode.petfinder.ui.base.ViewItem;
import com.greencode.petfinder.ui.pages.petSinglePage.viewitems.BigTextViewItem;
import com.greencode.petfinder.ui.viewmodels.baseModels.DoubleTextLineViewItem;
import com.greencode.petfinder.ui.viewmodels.baseModels.SectionViewItem;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class SinglePetFragment extends BaseFragment implements SinglePetContract.View,
        SimplePhotoAdapter.SimplePhotoAdapterClickListener {

    // TODO: 04.07.17 CHANGE IT
    /**
     * This is magic constant which equals to rows in recycler view before
     */
    private static final int MAGIC = 6;

    private SinglePetViewPagerAdapter photoViewPagerAdapter;

    private List<Photo> photos = new ArrayList<>();

    @Inject
    SinglePetPresenter singlePetPresenter;

    SinglePetComponent singlePetComponent;

    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    CirclePageIndicator mIndicator;
    SinglePetGodAdapter singlePetGodAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public SinglePetFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_single_pet, container, false);
        ButterKnife.bind(this, view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_arrow_back_white));

        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        photos.add(new Photo("1", getArguments().getString("url")));
        SinglePetViewPagerAdapter.SinglePetPhotoClickListener singlePetPhotoClickListener =
                position -> goToPhotoActivity(position);

        photoViewPagerAdapter = new SinglePetViewPagerAdapter(getActivity(), singlePetPhotoClickListener, photos);
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
        initRecyclerView();
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
        singlePetPresenter.loadPet(getArguments().getString("id"), false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem shareItem = menu.add("Share");
        shareItem.setIcon(R.drawable.ic_action_share_white);
        shareItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        shareItem.setOnMenuItemClickListener(item -> true);
    }

    private void initRecyclerView() {
        singlePetGodAdapter = new SinglePetGodAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < MAGIC) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(singlePetGodAdapter);
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
        viewItems.add(new DoubleTextLineViewItem("Name", pet.getName()));
        viewItems.add(new DoubleTextLineViewItem("Age", pet.getAge()));
        viewItems.add(new DoubleTextLineViewItem("Sex", pet.getSex()));
        viewItems.add(new SectionViewItem("About", R.color.colorPrimary));
        viewItems.add(new BigTextViewItem(pet.getDescription()));
        viewItems.add(new SectionViewItem("Neighbors", R.color.colorPrimary));
        singlePetGodAdapter.updateData(viewItems);
        singlePetPresenter.loadShelterNeighbor(pet.getShelterId(), 10);
    }

    @Override
    public void showNeighbors(List<SimplePetListItemView> pets) {
        List<ViewItem> viewItems = new ArrayList<>();
        viewItems.addAll(pets);
        singlePetGodAdapter.updateData(viewItems);
    }

    @Override
    public void onClick(@NonNull String id, @NonNull String url) {
        startActivity(new Intent(getActivity(), SinglePetActivity.class)
                .putExtra("id", id)
                .putExtra("url", url).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

    }

    @Override
    public void setPresenter(BasePresenter basePresenter) {
        basePresenter.destroy();
    }

    @Override
    public void showError(String text) {

    }

    @Override
    public void showLoading(boolean isLoading) {

    }

    private void goToPhotoActivity(int activePosition) {
        Intent intent = new Intent(getActivity(), PetPhotoViewActivity.class);
        ArrayList<String> urls = new ArrayList<String>() {{
            for (Photo p : photos) {
                add(p.getUrl());
            }
        }};
        intent.putStringArrayListExtra("photos", urls);
        intent.putExtra("position", activePosition);
        startActivity(intent);
    }

}
