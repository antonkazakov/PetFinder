package com.greencode.petfinder.ui.viewmodels.baseModels;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;
import com.greencode.petfinder.ui.base.ViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 15.05.17.
 */

public class DoubleTextLineAdapter implements DelegateAdapter<DoubleTextLineViewItemTemp> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DoubleTextLineViewHolder(inflater.inflate(R.layout.double_line_simple_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, DoubleTextLineViewItemTemp doubleTextLineViewItemTemp) {
        DoubleTextLineViewHolder doubleTextLineViewHolder = (DoubleTextLineViewHolder) holder;
        doubleTextLineViewHolder.tvPrimary.setText(doubleTextLineViewItemTemp.getPrimaryText());
        doubleTextLineViewHolder.tvSecondary.setText(doubleTextLineViewItemTemp.getSecondaryText());
    }

    public static class DoubleTextLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvPrimary)
        TextView tvPrimary;

        @BindView(R.id.tvSecondary)
        TextView tvSecondary;

        public DoubleTextLineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
