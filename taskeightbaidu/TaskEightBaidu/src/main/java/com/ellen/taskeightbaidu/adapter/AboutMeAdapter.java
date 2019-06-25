package com.ellen.taskeightbaidu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellen.taskeightbaidu.model.AboutMeDatum;
import com.ellen.taskeightbaidu.R;

/**
 * Created by ellen on 15/12/7.
 */
public class AboutMeAdapter extends RecyclerView.Adapter<AboutMeAdapter.ViewHoler> {

    private static AboutMeDatum[] aboutMeDatum;

    public AboutMeAdapter(AboutMeDatum[] aboutMeDatum) {
        this.aboutMeDatum = aboutMeDatum;
    }


    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_about_me, null);
        ViewHoler viewHoler = new ViewHoler(itemLayoutView);
        return viewHoler;
    }


    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {

        holder.tvAboutMe.setText(aboutMeDatum[position].getContent());
        holder.ivAboutMe.setImageResource(aboutMeDatum[position].getImageUrl());

    }

    public static class ViewHoler extends RecyclerView.ViewHolder {

        public TextView tvAboutMe;
        public ImageView ivAboutMe;


        public ViewHoler(final View itemLayoutView) {
            super(itemLayoutView);
            tvAboutMe = (TextView) itemLayoutView.findViewById(R.id.tvAboutMe);
            ivAboutMe = (ImageView) itemLayoutView.findViewById(R.id.ivAboutMe);
            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemLayoutView.getContext(),
                            "I was clicked! position=" + getLayoutPosition(),
                            Toast.LENGTH_SHORT).show();
                    System.out.println("RecyclerView was clicked!");
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return aboutMeDatum.length;
    }
}


