package com.greencode.petfinder.ui.screens.morePage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greencode.petfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoreFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public MoreFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
