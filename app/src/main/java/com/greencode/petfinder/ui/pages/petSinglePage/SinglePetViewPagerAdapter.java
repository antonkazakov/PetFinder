package com.greencode.petfinder.ui.pages.petSinglePage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.greencode.petfinder.R;
import com.greencode.petfinder.data.entity.locanbeans.pet.Photo;

import java.util.List;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class SinglePetViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Photo> photos;
    private SinglePetPhotoClickListener singlePetPhotoClickListener;


    public SinglePetViewPagerAdapter(Context context, SinglePetPhotoClickListener singlePetPhotoClickListener, List<Photo> photos) {
        this.context = context;
        this.photos = photos;
        this.singlePetPhotoClickListener = singlePetPhotoClickListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlepet_vp_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setOnClickListener(v -> singlePetPhotoClickListener.onPhotoClicked(position));
        Glide.with(context).load(photos.get(position).getUrl()).dontAnimate().into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public interface SinglePetPhotoClickListener {

        void onPhotoClicked(int position);

    }

}
