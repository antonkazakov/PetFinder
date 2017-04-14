package com.greencode.petfinder.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public interface DelegateAdapter {

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

}
