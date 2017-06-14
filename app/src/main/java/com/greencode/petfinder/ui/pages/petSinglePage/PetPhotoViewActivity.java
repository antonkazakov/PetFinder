package com.greencode.petfinder.ui.pages.petSinglePage;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;

public class PetPhotoViewActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    PhotoPagerAdapter photoPagerAdapter;

    List<String> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.drawable.ic_action_arrow_back_white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        photoList = getIntent().getStringArrayListExtra("photos");
        photoPagerAdapter = new PhotoPagerAdapter(photoList);
        viewPager.setAdapter(photoPagerAdapter);
        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0), false);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_pet_photo_view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Adapter for pager on this activity
     */
    private class PhotoPagerAdapter extends PagerAdapter {

        private List<String> photos;

        public PhotoPagerAdapter(List<String> photos) {
            this.photos = photos;
        }

        @Override
        public int getCount() {
            return photos.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.photo_item, container, false);
            PhotoView photoView = (PhotoView) view.findViewById(R.id.photoView);
            Glide.with(container.getContext())
                    .load(photos.get(position))
                    .dontAnimate()
                    .into(photoView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }



}
