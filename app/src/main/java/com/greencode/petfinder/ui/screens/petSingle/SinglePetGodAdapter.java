package com.greencode.petfinder.ui.screens.petSingle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;
import com.greencode.petfinder.ui.base.ViewItem;
import com.greencode.petfinder.ui.screens.petSingle.viewitems.BitTextDelegateAdapter;
import com.greencode.petfinder.ui.viewmodels.baseModels.SectionAdapter;
import com.greencode.petfinder.ui.screens.shelterSingle.ShelterSimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Kazakov
 * @date 26.04.17.
 */

public class SinglePetGodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ViewItem> viewItems = new ArrayList<>();
    private Map<Integer, DelegateAdapter> adapterMap = new HashMap<>();

    public SinglePetGodAdapter() {
        adapterMap.put(R.layout.shelter_page_simple_item, new ShelterSimpleAdapter());
        adapterMap.put(R.layout.big_text_item, new BitTextDelegateAdapter());
        adapterMap.put(R.layout.section_item, new SectionAdapter());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterMap.get(viewType).onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapterMap.get(holder.getItemViewType()).onBindViewHolder(holder, viewItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return viewItems.get(position).viewType();
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    void updateData(List<ViewItem> viewItems1){
        viewItems.addAll(viewItems1);
        notifyDataSetChanged();
    }

    void updateData(ViewItem viewItem){
        viewItems.add(viewItem);
        notifyDataSetChanged();
    }

}
