package com.greencode.petfinder.ui.viewmodels.baseModels;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;
import com.greencode.petfinder.ui.viewmodels.baseModels.SectionViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 30.04.17.
 */

public class SectionAdapter implements DelegateAdapter<SectionViewItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new SectionViewHolder(inflater.inflate(R.layout.section_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, SectionViewItem shelterPageSectionItem) {
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.tvValue.setText(shelterPageSectionItem.getTextValue());
        sectionViewHolder.tvValue.setTextColor(ContextCompat.getColor(sectionViewHolder.itemView.getContext(),shelterPageSectionItem.getTextColor()));
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
