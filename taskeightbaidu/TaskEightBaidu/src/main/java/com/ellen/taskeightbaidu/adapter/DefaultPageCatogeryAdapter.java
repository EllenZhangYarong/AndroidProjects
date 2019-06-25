package com.ellen.taskeightbaidu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.taskeightbaidu.model.DefaultPageCatogeryDatum;
import com.ellen.taskeightbaidu.R;

/**
 * Created by ellen on 15/12/7.
 */
public class DefaultPageCatogeryAdapter extends RecyclerView.Adapter<DefaultPageCatogeryAdapter.ViewHoler> {

    private DefaultPageCatogeryDatum[] defaultPageCatogeryDatums;

    public DefaultPageCatogeryAdapter(DefaultPageCatogeryDatum[] defaultPageCatogeryDatums) {
        this.defaultPageCatogeryDatums = defaultPageCatogeryDatums;
    }


    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_default_catogery, null);
        ViewHoler viewHoler = new ViewHoler(itemLayoutView);
        return viewHoler;
    }


    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {

        holder.tvDefaultPageCategoryAdd.setText(defaultPageCatogeryDatums[position].getHint());
        holder.tvDefaultPageCategory.setText(defaultPageCatogeryDatums[position].getCatogery());
        holder.ivDefaultPageCategory.setImageResource(defaultPageCatogeryDatums[position].getImageUrl());


    }

    public static class ViewHoler extends RecyclerView.ViewHolder {

        public TextView tvDefaultPageCategoryAdd;
        public ImageView ivDefaultPageCategory;
        public TextView tvDefaultPageCategory;


        public ViewHoler(View itemLayoutView) {
            super(itemLayoutView);
            tvDefaultPageCategoryAdd = (TextView) itemLayoutView.findViewById(R.id.tvDefaultPageCategoryAdd);
            tvDefaultPageCategory = (TextView) itemLayoutView.findViewById(R.id.tvDefaultPageCategory);
            ivDefaultPageCategory = (ImageView) itemLayoutView.findViewById(R.id.ivDefaultPageCategory);
        }
    }


    @Override
    public int getItemCount() {
        return defaultPageCatogeryDatums.length;
    }
}


