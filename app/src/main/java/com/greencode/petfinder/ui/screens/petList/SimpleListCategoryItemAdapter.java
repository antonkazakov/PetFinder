package com.greencode.petfinder.ui.screens.petList;

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
 * @date 26.04.17.
 */

public class SimpleListCategoryItemAdapter implements DelegateAdapter<DoubleTextLineViewItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new SimpleListCategoryItemViewHolder(inflater.inflate(R.layout.pet_list_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, DoubleTextLineViewItem simpleListCategoryItem) {
        SimpleListCategoryItemViewHolder simpleListCategoryItemViewHolder = (SimpleListCategoryItemViewHolder) holder;
        simpleListCategoryItemViewHolder.tvCategoryName.setText(simpleListCategoryItem.getPrimaryText());
    }

    public static class SimpleListCategoryItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.categoryName)
        TextView tvCategoryName;

        public SimpleListCategoryItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
