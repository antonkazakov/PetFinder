package com.greencode.petfinder.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.greencode.petfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 20.04.17.
 */

public class SquarePhotoItemAdapter implements DelegateAdapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new SquarePhotoViewHolder(inflater.inflate(R.layout.square_photo_item, parent));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewItem viewItem) {

    }

    private static class SquarePhotoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.photoView)
        ImageView photoView;

        public SquarePhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
