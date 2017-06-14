package com.greencode.petfinder.ui.pages.shelterSinglePage;

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
 * @date 29.04.17.
 */

public class ShelterHeaderDelegateAdapter implements DelegateAdapter<ShelterPageHeaderViewItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ShelterPagerHeaderViewHolder(inflater.inflate(R.layout.shelter_page_header_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ShelterPageHeaderViewItem shelterPageHeaderViewItem) {
        ShelterPagerHeaderViewHolder shelterPagerHeaderViewHolder = (ShelterPagerHeaderViewHolder) holder;
        Glide.with(holder.itemView.getContext())
                .load(shelterPageHeaderViewItem.getPhotoUrl())
                .centerCrop()
                .into(shelterPagerHeaderViewHolder.imgMap);
    }

    public static class ShelterPagerHeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgMap)
        ImageView imgMap;

        public ShelterPagerHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
