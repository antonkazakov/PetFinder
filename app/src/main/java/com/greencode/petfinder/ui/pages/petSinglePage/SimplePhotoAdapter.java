package com.greencode.petfinder.ui.pages.petSinglePage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 04.07.17.
 */

public class SimplePhotoAdapter implements DelegateAdapter<SimplePetListItemView> {

    private SimplePhotoAdapterClickListener simplePhotoAdapterClickListener;

    public SimplePhotoAdapter(SimplePhotoAdapterClickListener simplePhotoAdapterClickListener) {
        this.simplePhotoAdapterClickListener = simplePhotoAdapterClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new SimplePhotoViewHolder(inflater.inflate(R.layout.square_photo_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, SimplePetListItemView simplePetListItemView) {
        SimplePhotoViewHolder simplePhotoViewHolder = (SimplePhotoViewHolder) holder;
        Glide.with(simplePhotoViewHolder.itemView.getContext())
                .load(simplePetListItemView.getPhotoUrl())
                .centerCrop()
                .into(simplePhotoViewHolder.imgPhoto);
        simplePhotoViewHolder.itemView.setOnClickListener(
                view -> simplePhotoAdapterClickListener.onClick(
                        simplePetListItemView.getId(),
                        simplePetListItemView.getPhotoUrl()));
    }

    public static class SimplePhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_photo)
        ImageView imgPhoto;

        public SimplePhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface SimplePhotoAdapterClickListener {

        void onClick(@NonNull String id, @NonNull String url);

    }

}
