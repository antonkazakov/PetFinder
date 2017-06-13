package com.greencode.petfinder.ui.screens.petSingle.viewitems;

import android.support.annotation.NonNull;
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

public class BitTextDelegateAdapter implements DelegateAdapter<BigTextViewItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BigTextViewHolder(inflater.inflate(R.layout.big_text_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, BigTextViewItem bigTextViewItem) {
        BigTextViewHolder bigTextViewHolder = (BigTextViewHolder) holder;
        bigTextViewHolder.tvText.setText(bigTextViewItem.getText());
    }

    public static class BigTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvText)
        TextView tvText;

        public BigTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
