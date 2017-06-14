package com.greencode.petfinder.ui.pages.petListPage;

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
 * @date 25.04.17.
 */

public class LuckyPetListItemViewAdapter implements DelegateAdapter<LuckyPetListItemView> {

    private SinglePetClickListener singlePetClickListener;

    public LuckyPetListItemViewAdapter(SinglePetClickListener singlePetClickListener) {
        this.singlePetClickListener = singlePetClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LuckyPetListItemViewHolder(inflater.inflate(R.layout.luckypet_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, LuckyPetListItemView luckyPetListItemView) {
        LuckyPetListItemViewHolder luckyPetListItemViewHolder = (LuckyPetListItemViewHolder) holder;
        Glide.with(luckyPetListItemViewHolder.itemView.getContext())
                .load(luckyPetListItemView.getPhotoUrl())
                .into(luckyPetListItemViewHolder.imgAvatar);
    }

    public static class LuckyPetListItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgAvatar)
        ImageView imgAvatar;

        public LuckyPetListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
