package com.greencode.petfinder.ui.pages.petSearchPage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.screens.petSearch.SimpleFilterItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Anton Kazakov
 * @date 28.04.17.
 */

public class SearchFiltersAdapter extends RecyclerView.Adapter<SearchFiltersAdapter.SearchFilterViewHolder> {

    private List<SimpleFilterItem> simpleFilterItems;
    private SearchFiltersClickListener searchFiltersClickListener;

    public SearchFiltersAdapter(List<SimpleFilterItem> simpleFilterItems, SearchFiltersClickListener searchFiltersClickListener) {
        this.simpleFilterItems = simpleFilterItems;
        this.searchFiltersClickListener = searchFiltersClickListener;
    }

    @Override
    public SearchFilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchFilterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_filter_item, null));
    }

    @Override
    public void onBindViewHolder(SearchFilterViewHolder holder, int position) {
        SimpleFilterItem simpleFilterItem = simpleFilterItems.get(position);
        holder.tvTitle.setText(simpleFilterItem.getTitle());
        holder.tvValue.setText(simpleFilterItem.getValue());
        holder.itemView.setOnClickListener(v -> {
            if (searchFiltersClickListener != null) {
                searchFiltersClickListener.onFilterCLick(position, simpleFilterItem.getType());
            }
        });
    }

    void changeItem(int index, SimpleFilterItem simpleFilterItem) {
        simpleFilterItems.remove(index);
        simpleFilterItems.add(index, simpleFilterItem);
        notifyItemChanged(index);
    }

    @Override
    public int getItemCount() {
        return simpleFilterItems.size();
    }

    public static class SearchFilterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvValue)
        TextView tvValue;

        public SearchFilterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface SearchFiltersClickListener {

        void onFilterCLick(int position, String type);

    }

}
