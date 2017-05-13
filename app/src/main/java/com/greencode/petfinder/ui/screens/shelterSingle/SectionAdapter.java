package com.greencode.petfinder.ui.screens.shelterSingle;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 30.04.17.
 */

public class SectionAdapter implements DelegateAdapter<ShelterPageSectionItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new SectionViewHolder(inflater.inflate(R.layout.section_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ShelterPageSectionItem shelterPageSectionItem) {
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.tvValue.setText(shelterPageSectionItem.getValue());
        sectionViewHolder.tvValue.setTextColor(ContextCompat.getColor(sectionViewHolder.itemView.getContext(),shelterPageSectionItem.getColor()));
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvValue)
        TextView tvValue;

        public SectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
