package com.greencode.petfinder.ui.pages.morePage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;
import com.greencode.petfinder.ui.base.ViewItem;
import com.greencode.petfinder.ui.viewmodels.baseModels.DoubleTextLineAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Kazakov
 * @date 15.05.17.
 */

public class MorePageGodAdapter extends RecyclerView.Adapter {

    private Map<Integer, DelegateAdapter> adapters = new HashMap<>();

    private List<ViewItem> viewItemList = new ArrayList<>();

    public MorePageGodAdapter() {
        adapters.put(R.layout.double_line_simple_item, new DoubleTextLineAdapter());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapters.get(viewType).onCreateViewHolder(LayoutInflater.from(parent.getContext()),parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapters.get(holder.getItemViewType()).onBindViewHolder(holder, viewItemList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return viewItemList.get(position).viewType();
    }

    public void addAllData(List<ViewItem> viewItems){
        if (!viewItemList.isEmpty())
            viewItemList.clear();
        viewItemList.addAll(viewItems);
    }

    public void addData(ViewItem viewItem){
        viewItemList.add(viewItem);
    }

    @Override
    public int getItemCount() {
        return viewItemList.size();
    }


}
