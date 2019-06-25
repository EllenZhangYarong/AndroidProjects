package com.ellen.taskeightbaidufirstpage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.taskeightbaidufirstpage.R;
import com.ellen.taskeightbaidufirstpage.models.CatogeryDatum;

/**
 * Created by ellen on 15/12/9.
 */
public class DefaultCatogeryAdapter extends RecyclerView.Adapter<DefaultCatogeryAdapter.DefaultCatogeryViewHolder> {


    private CatogeryDatum[] catogeryDatum;


    //Second Step: Create a constructor for adapter to acept kind of datum
    public DefaultCatogeryAdapter(CatogeryDatum[] catogeryDatum) {

        this.catogeryDatum = catogeryDatum;

    }


    //Third Step: Create ViewHolder
    @Override
    public DefaultCatogeryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_default_catogery, parent, false);

        DefaultCatogeryViewHolder defaultCatogeryViewHolder = new DefaultCatogeryViewHolder(view);

        return defaultCatogeryViewHolder;
    }


    //Four Step: Bind ViewHolder. Bind datum and component, set value
    public void onBindViewHolder(DefaultCatogeryViewHolder holder, int position) {

        holder.ivCategoryImage.setImageResource(catogeryDatum[position].getImageUrl());
        holder.tvDefaultCategory.setText(catogeryDatum[position].getContent());
        holder.tvDefaultAdcorner.setText(catogeryDatum[position].getAdCorner());
    }

    @Override
    public int getItemCount() {
        return catogeryDatum.length;
    }


    //First Step : Creat a ViewHolder class
    protected static class DefaultCatogeryViewHolder extends RecyclerView.ViewHolder {

        //Components in item layout. Holder means you are in my arms

        ImageView ivCategoryImage;
        TextView tvDefaultCategory, tvDefaultAdcorner;


        public DefaultCatogeryViewHolder(View itemView) {
            super(itemView);
            ivCategoryImage = (ImageView) itemView.findViewById(R.id.ivCategoryImage);
            tvDefaultCategory = (TextView) itemView.findViewById(R.id.tvDefaultCategory);
            tvDefaultAdcorner = (TextView) itemView.findViewById(R.id.tvDefaultAdcorner);
        }
    }


}
