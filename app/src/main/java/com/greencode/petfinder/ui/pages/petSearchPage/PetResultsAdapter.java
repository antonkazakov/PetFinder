package com.greencode.petfinder.ui.pages.petSearchPage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

public class PetResultsAdapter extends RecyclerView.Adapter<PetResultsAdapter.PetResultsViewHolder> {

    private List<PetSearchResultsItemView> petSearchResultsItemViews = new ArrayList<>();

    @Override
    public PetResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PetResultsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shelter_page_simple_item, null));
    }

    @Override
    public void onBindViewHolder(PetResultsViewHolder holder, int position) {

        Log.i("sfsdf", "onBindViewHolder: ");
        holder.tvName.setText(petSearchResultsItemViews.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return petSearchResultsItemViews.size();
    }

    public void updateData(List<PetSearchResultsItemView> petSearchResultsItemViews) {
        this.petSearchResultsItemViews.addAll(petSearchResultsItemViews);
        notifyDataSetChanged();
    }

    public static class PetResultsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvDescription)
        TextView tvDesc;

        public PetResultsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

}
