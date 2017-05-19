package com.greencode.petfinder.ui.screens.petList;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 20.04.17.
 */

public class SinglePetListItemViewAdapter implements DelegateAdapter<SinglePetListItemView> {

    private SinglePetClickListenerExtended singlePetClickListener;

    public SinglePetListItemViewAdapter(SinglePetClickListenerExtended singlePetClickListener) {
        this.singlePetClickListener = singlePetClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new SinglePetListItemViewHolder(inflater.inflate(R.layout.pet_list_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, SinglePetListItemView testFuckItem) {
        SinglePetListItemViewHolder singlePetListItemViewHolder = (SinglePetListItemViewHolder) holder;
        singlePetListItemViewHolder.tvName.setText(testFuckItem.getName());
        singlePetListItemViewHolder.tvDescription.setText(testFuckItem.getDescription());
        Glide.with(holder.itemView.getContext())
                .load(testFuckItem.getPhotoUrl())
                .centerCrop()
                .dontAnimate()
                .into(singlePetListItemViewHolder.imgAvatar);
    }


    /**
     * Should ViewHolder be static?
     * http://stackoverflow.com/questions/20380600/gc-performance-hit-for-inner-class-vs-static-nested-class
     */
    public static class SinglePetListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutLeft)
        CardView left;

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.imgAvatar)
        ImageView imgAvatar;

        @BindView(R.id.tvDescription)
        TextView tvDescription;

        public SinglePetListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
