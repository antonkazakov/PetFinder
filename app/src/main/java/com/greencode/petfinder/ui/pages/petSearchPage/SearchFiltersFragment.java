package com.greencode.petfinder.ui.pages.petSearchPage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.screens.petSearch.SimpleFilterItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFiltersFragment extends Fragment implements SearchFiltersAdapter.SearchFiltersClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    SearchFiltersAdapter searchAdapter;

    OnSearchStartListener onSearchStartListener;

    public SearchFiltersFragment() {
        // Required empty public constructor
    }

    public void setSearchListener(OnSearchStartListener onSearchStartListener) {
        this.onSearchStartListener = onSearchStartListener;
    }

    private Map<String, String> filtersMap = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_filters, container, false);
        ButterKnife.bind(this, view);
        List<SimpleFilterItem> list = new ArrayList<>();
        list.add(new SimpleFilterItem("Animal", null, SimpleFilterItem.TYPE_ANIMAL));
        list.add(new SimpleFilterItem("Size", null, SimpleFilterItem.TYPE_SIZE));
        list.add(new SimpleFilterItem("Sex", null, SimpleFilterItem.TYPE_SEX));
        list.add(new SimpleFilterItem("Age", null, SimpleFilterItem.TYPE_AGE));
        searchAdapter = new SearchFiltersAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(searchAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem shareItem = menu.add("Show");
        shareItem.setTitle("Show");
        shareItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        shareItem.setOnMenuItemClickListener(item -> {
            onSearchStartListener.onFiltersAssembled(filtersMap);
            return true;
        });
    }

    @Override
    public void onFilterCLick(int position, String type) {
        switch (type) {
            case SimpleFilterItem.TYPE_ANIMAL:
                buildMultipleDialog("Animal", type, getActivity().getResources().getStringArray(R.array.animals), position);
                break;
            case SimpleFilterItem.TYPE_SIZE:
                buildMultipleDialog("Animal Size", type, getActivity().getResources().getStringArray(R.array.sizes), position);
                break;
            case SimpleFilterItem.TYPE_SEX:
                buildMultipleDialog("Animal Sex", type, getActivity().getResources().getStringArray(R.array.sexes), position);
                break;
            case SimpleFilterItem.TYPE_AGE:
                buildMultipleDialog("Animal Age", type, getActivity().getResources().getStringArray(R.array.ages), position);
                break;
        }
    }

    private void buildMultipleDialog(String title, String type, String[] array, int position) {
        new MaterialDialog.Builder(getActivity())
                .title(title)
                .items(array)
                .itemsCallbackMultiChoice(null, (dialog, which, text) -> {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < which.length; i++) {
                        str.append(text[i]);
                        if (i != which.length - 1) {
                            str.append(",");
                        }
                    }
                    searchAdapter.changeItem(position, new SimpleFilterItem(title, str.toString(), type));
                    filtersMap.put(type, str.toString());
                    return true;
                })
                .positiveText("OK")
                .show();
    }

    public interface OnSearchStartListener {

        void onFiltersAssembled(Map<String, String> filtersMap);

    }

}
