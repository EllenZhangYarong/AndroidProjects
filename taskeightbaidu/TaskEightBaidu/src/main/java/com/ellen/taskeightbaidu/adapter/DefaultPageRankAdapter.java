package com.ellen.taskeightbaidu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.taskeightbaidu.R;
import com.ellen.taskeightbaidu.model.DefaultPageCatogeryDatum;

/**
 * Created by ellen on 15/12/7.
 */
public class DefaultPageRankAdapter extends RecyclerView.Adapter<DefaultPageRankAdapter.ViewHoler> {

    private DefaultPageCatogeryDatum[] defaultPageCatogeryDatums;

    public DefaultPageRankAdapter(DefaultPageCatogeryDatum[] defaultPageCatogeryDatums) {

        System.out.println("DefaultPageRankAdapter--->" +defaultPageCatogeryDatums.length);
        this.defaultPageCatogeryDatums = defaultPageCatogeryDatums;

    }


    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_default_rank, null);
        ViewHoler viewHoler = new ViewHoler(itemLayoutView);
        return viewHoler;
    }


    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {

        System.out.println("onBindViewHolder-----catogety--->" +
                defaultPageCatogeryDatums[position].getCatogery());

        holder.tvDefaultRankBig.setText(defaultPageCatogeryDatums[position].getHint());
        holder.tvDefaultRankSmall.setText(defaultPageCatogeryDatums[position].getCatogery());
        holder.ivDefaultRank.setImageResource(defaultPageCatogeryDatums[position].getImageUrl());


    }

    public static class ViewHoler extends RecyclerView.ViewHolder {

        public TextView tvDefaultRankBig;
        public ImageView ivDefaultRank;
        public TextView tvDefaultRankSmall;


        public ViewHoler(View itemLayoutView) {
            super(itemLayoutView);
            tvDefaultRankBig = (TextView) itemLayoutView.findViewById(R.id.tvDefaultRankBig);
            tvDefaultRankSmall = (TextView) itemLayoutView.findViewById(R.id.tvDefaultRankSmall);
            ivDefaultRank = (ImageView) itemLayoutView.findViewById(R.id.ivDefaultRank);
        }
    }


    @Override
    public int getItemCount() {
        return defaultPageCatogeryDatums.length;
    }
}


