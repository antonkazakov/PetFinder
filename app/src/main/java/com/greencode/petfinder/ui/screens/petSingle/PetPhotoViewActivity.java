package com.greencode.petfinder.ui.screens.petSingle;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.greencode.petfinder.R;
import com.greencode.petfinder.data.entity.locanbeans.pet.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetPhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    PhotoPagerAdapter photoPagerAdapter;

    List<Photo> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_photo_view);
        ButterKnife.bind(this);
        photoList = getIntent().getParcelableArrayListExtra("photos");
        photoPagerAdapter = new PhotoPagerAdapter(photoList);
        viewPager.setAdapter(photoPagerAdapter);
        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0), false);
    }

    /**
     * Adapter for pager on this activity
     */
    private static class PhotoPagerAdapter extends PagerAdapter {

        private List<Photo> photos;

        public PhotoPagerAdapter(List<Photo> photos) {
            this.photos = photos;
        }

        @Override
        public int getCount() {
            return photos.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(container.getContext())
                    .load(photos.get(position).getUrl())
                    .into(photoView);
            return super.instantiateItem(container, position);
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
