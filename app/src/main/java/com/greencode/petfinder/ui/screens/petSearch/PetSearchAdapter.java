package com.greencode.petfinder.ui.screens.petSearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.screens.petList.SinglePetListItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 28.04.17.
 */

public class PetSearchAdapter extends RecyclerView.Adapter<PetSearchAdapter.PetSearchViewHolder> {

    private List<SinglePetListItemView> petListItemViews;

    public PetSearchAdapter(List<SinglePetListItemView> petListItemViews) {
        this.petListItemViews = petListItemViews;
    }

    @Override
    public PetSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PetSearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent));
    }

    @Override
    public void onBindViewHolder(PetSearchViewHolder holder, int position) {
        holder.tvName.setText(petListItemViews.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return petListItemViews.size();
    }

    public static class PetSearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        public PetSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
