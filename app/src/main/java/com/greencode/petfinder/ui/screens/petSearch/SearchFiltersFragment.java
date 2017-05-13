package com.greencode.petfinder.ui.screens.petSearch;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.greencode.petfinder.R;

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

    public SearchFiltersFragment() {
        // Required empty public constructor
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
            return true;
        });
    }

    @Override
    public void onFilterCLick(String type) {
        switch (type){
            case SimpleFilterItem.TYPE_ANIMAL:
                buildMultipleDialog("Animal", type, getActivity().getResources().getStringArray(R.array.animals));
                break;
            case SimpleFilterItem.TYPE_SIZE:
                buildMultipleDialog("Animal Size", type, getActivity().getResources().getStringArray(R.array.sizes));
                break;
            case SimpleFilterItem.TYPE_SEX:
                buildMultipleDialog("Animal Sex", type, getActivity().getResources().getStringArray(R.array.sexes));
                break;
            case SimpleFilterItem.TYPE_AGE:
                buildMultipleDialog("Animal Age", type, getActivity().getResources().getStringArray(R.array.ages));
                break;
        }
    }

    private void buildMultipleDialog(String title, String type, String[] array){
        new MaterialDialog.Builder(getActivity())
                .title(title)
                .items(array)
                .itemsCallbackMultiChoice(null, (dialog, which, text) -> {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < which.length; i++) {
                            str.append(text[i]);
                            if (i != which.length-1) {
                                str.append(",");
                            }
                    }
                    filtersMap.put(type, str.toString());
                    return true;
                })
                .positiveText("OK")
                .show();
    }

    public static void applyFontForToolbarTitle(Activity context){

        Toolbar toolbar = (Toolbar) context.findViewById(R.id.toolbar);
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                tv.setText("dsfsdf");
                break;
            }
        }
    }

}
