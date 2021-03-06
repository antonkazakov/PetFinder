package com.greencode.petfinder.ui.pages.shelterSinglePage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;
import com.greencode.petfinder.ui.base.ViewItem;
import com.greencode.petfinder.ui.pages.petListPage.SinglePetClickListenerExtended;
import com.greencode.petfinder.ui.pages.petListPage.SinglePetListItemView;
import com.greencode.petfinder.ui.pages.petListPage.SinglePetListItemViewAdapter;
import com.greencode.petfinder.ui.viewmodels.baseModels.FooterAdapter;
import com.greencode.petfinder.ui.viewmodels.baseModels.FooterViewItem;
import com.greencode.petfinder.ui.viewmodels.baseModels.SectionAdapter;
import com.greencode.petfinder.ui.viewmodels.baseModels.SectionViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Kazakov
 * @date 23.04.17.
 */

public class ShelterPageGodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Map<Integer, DelegateAdapter> adapters = new HashMap<>();

    private List<ViewItem> viewItems = new ArrayList<>();

    public ShelterPageGodAdapter(SinglePetClickListenerExtended singlePetClickListener) {
        adapters.put(R.layout.shelter_page_header_item, new ShelterHeaderDelegateAdapter());
        adapters.put(R.layout.shelter_page_simple_item, new ShelterSimpleAdapter());
        adapters.put(R.layout.pet_list_single_item, new SinglePetListItemViewAdapter(singlePetClickListener));
        adapters.put(R.layout.section_item, new SectionAdapter());
        adapters.put(R.layout.footer_itemview, new FooterAdapter());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapters.get(viewType).onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapters.get(holder.getItemViewType()).onBindViewHolder(holder, viewItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return viewItems.get(position).viewType();
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    public void updateData(List<ViewItem> viewItems1){
        viewItems.addAll(viewItems1);
        notifyDataSetChanged();
    }

    public void updateData1(List<SinglePetListItemView> viewItems12){
        viewItems.add(new SectionViewItem("Our Pets", R.color.greenPrimary));
        viewItems.addAll(viewItems12);
        viewItems.add(new FooterViewItem("Loaded" + viewItems12.size() + "items"));
        notifyDataSetChanged();
    }

}
