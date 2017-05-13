package com.greencode.petfinder.ui.screens.petList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;
import com.greencode.petfinder.ui.base.ViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Kazakov
 * @date 24.04.17.
 */

public class PetListGodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Map<Integer, DelegateAdapter> adapterMap = new HashMap<>();

    private List<ViewItem> viewItems;

    public PetListGodAdapter(SinglePetClickListenerExtended singlePetClickListener) {
        viewItems = new ArrayList<>();
        adapterMap.put(R.layout.pet_list_category_item, new SimpleListCategoryItemAdapter());
        adapterMap.put(R.layout.pet_list_single_item, new SinglePetListItemViewAdapter(singlePetClickListener));
        adapterMap.put(R.layout.luckypet_list_item, new LuckyPetListItemViewAdapter(singlePetClickListener));
        setHasStableIds(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterMap.get(viewType).onCreateViewHolder(LayoutInflater.from(parent.getContext()),parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapterMap.get(holder.getItemViewType()).onBindViewHolder(holder, viewItems.get(position));
    }

    @Override
    public long getItemId(int position) {
        return viewItems.get(position).hashCode();
    }

    @Override
    public int getItemViewType(int position) {
        return viewItems.get(position).viewType();
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    public void updateData(List<ViewItem> list){
        viewItems.addAll(list);
        notifyDataSetChanged();
    }

    public void updateData(ViewItem viewItem){
        viewItems.add(viewItem);
        notifyDataSetChanged();
    }

}
