package com.ellen.taskeightbaidufirstpage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellen.taskeightbaidufirstpage.R;
import com.ellen.taskeightbaidufirstpage.models.AboutMeDatum;

/**
 * Created by ellen on 15/12/7.
 */
public class AboutMeAdapter extends RecyclerView.Adapter<AboutMeAdapter.ViewHoler> {

    private AboutMeDatum[] aboutMeDatum;

    public AboutMeAdapter(AboutMeDatum[] aboutMeDatum) {
        this.aboutMeDatum = aboutMeDatum;
    }


    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

//        LayoutInflater layoutInflater =
//                (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_about_me, parent, false);

        ViewHoler viewHoler = new ViewHoler(itemLayoutView);
        return viewHoler;
    }


    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {

        holder.tvAboutMe.setText(aboutMeDatum[position].getContent());
        holder.ivAboutMe.setImageResource(aboutMeDatum[position].getImageUrl());

    }

    @Override
    public int getItemCount() {
        return aboutMeDatum.length;
    }

    public static class ViewHoler extends RecyclerView.ViewHolder {

        public TextView tvAboutMe;
        public ImageView ivAboutMe;


        public ViewHoler(final View itemLayoutView) {
            super(itemLayoutView);

            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemLayoutView.getContext(),
                            "I was clicked! position=" + getLayoutPosition(),
                            Toast.LENGTH_SHORT).show();
                    System.out.println("RecyclerView was clicked!");
                }
            });

            tvAboutMe = (TextView) itemLayoutView.findViewById(R.id.tvAboutMe);
            ivAboutMe = (ImageView) itemLayoutView.findViewById(R.id.ivAboutMe);
        }

    }
}


