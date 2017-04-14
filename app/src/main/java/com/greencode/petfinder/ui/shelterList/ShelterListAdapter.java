package com.greencode.petfinder.ui.shelterList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.viewmodels.ShelterListViewModel;

import java.util.List;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public class ShelterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ShelterListViewModel> shelterListViewModels;

    public ShelterListAdapter(List<ShelterListViewModel> shelterListViewModels) {
        this.shelterListViewModels = shelterListViewModels;
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
        shelterViewHolder.tvDistance.setText(shelterListViewModels.get(position).getDistanceFromPosition());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return shelterListViewModels.size();
    }

    public class ShelterViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDistance;

        public ShelterViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDistance = (TextView) itemView.findViewById(R.id.tvDistance);
        }

    }

}
