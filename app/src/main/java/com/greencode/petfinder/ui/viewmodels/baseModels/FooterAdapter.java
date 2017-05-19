package com.greencode.petfinder.ui.viewmodels.baseModels;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greencode.petfinder.R;
import com.greencode.petfinder.ui.base.DelegateAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 19.05.17.
 */

public class FooterAdapter implements DelegateAdapter<FooterViewItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new FooterVH(inflater.inflate(R.layout.footer_itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, FooterViewItem footerViewItem) {
        FooterVH footerVH = (FooterVH) holder;
        footerVH.tvFooter.setText(footerViewItem.getText());
    }
    public static class FooterVH extends RecyclerView.ViewHolder{

        @BindView(R.id.tvFooter)
        TextView tvFooter;

        public FooterVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
