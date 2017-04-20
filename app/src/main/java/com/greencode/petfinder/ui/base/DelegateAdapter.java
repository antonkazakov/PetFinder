package com.greencode.petfinder.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public interface DelegateAdapter {

    RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    void onBindViewHolder(RecyclerView.ViewHolder holder, ViewItem viewItem);

}
