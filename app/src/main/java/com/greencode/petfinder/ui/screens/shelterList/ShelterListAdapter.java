package com.greencode.petfinder.ui.screens.shelterList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.viewmodels.ShelterListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public class ShelterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ShelterListViewModel> shelterListViewModels;

    private ShelterClickListener shelterClickListener;

    public ShelterListAdapter(List<ShelterListViewModel> shelterListViewModels, ShelterClickListener shelterClickListener) {
        this.shelterListViewModels = shelterListViewModels;
        this.shelterClickListener = shelterClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shelters_list_item, parent, false);
        ShelterViewHolder shelterViewHolder = new ShelterViewHolder(view);
        return shelterViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShelterViewHolder shelterViewHolder = (ShelterViewHolder) holder;
        shelterViewHolder.tvName.setText(shelterListViewModels.get(position).getName());
        shelterViewHolder.tvDescription.setText(shelterListViewModels.get(position).getAddress());
        shelterViewHolder.itemView.setOnClickListener(v -> shelterClickListener.onShelterClicked(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return shelterListViewModels.size();
    }

    public static class ShelterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvDescription)
        TextView tvDescription;

        public ShelterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
