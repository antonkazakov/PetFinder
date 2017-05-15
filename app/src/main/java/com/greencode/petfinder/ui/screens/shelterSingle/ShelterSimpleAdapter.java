package com.greencode.petfinder.ui.screens.shelterSingle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;
import com.greencode.petfinder.ui.viewmodels.baseModels.DoubleTextLineViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 29.04.17.
 */

public class ShelterSimpleAdapter implements DelegateAdapter<DoubleTextLineViewItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ShelterSimpleItemViewHolder(inflater.inflate(R.layout.shelter_page_simple_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, DoubleTextLineViewItem simpleShelterItemView) {
        ShelterSimpleItemViewHolder shelterSimpleItemViewHolder = (ShelterSimpleItemViewHolder) holder;
        shelterSimpleItemViewHolder.tvName.setText(simpleShelterItemView.getPrimaryText());
        shelterSimpleItemViewHolder.tvValue.setText(simpleShelterItemView.getSecondaryText());
    }

    public static class ShelterSimpleItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvDescription)
        TextView tvValue;

        public ShelterSimpleItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
