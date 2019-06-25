package com.ellen.taskninefragment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.taskninefragment.R;
import com.ellen.taskninefragment.models.ActorsModel;

import java.util.List;

/**
 * Created by ellen on 15/12/14.
 */
public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    private List<ActorsModel> actorsList;

    public ActorAdapter(List<ActorsModel> actorsList) {
        this.actorsList = actorsList;
    }


    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actors_cardview, parent, false);

        ActorViewHolder actorViewHolder = new ActorViewHolder(view);

        return actorViewHolder;


    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        ActorsModel am = actorsList.get(position);
        holder.ivActorPhoto.setImageResource(am.getImgUri());
        holder.tvName.setText(am.getName());
        holder.tvActorName.setText(am.getActorName());
        holder.tvBriefIntro.setText(am.getAbriefIntro());


    }

    @Override
    public int getItemCount() {
        return actorsList.size();
    }

    protected static class ActorViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivActorPhoto;
        private TextView tvName, tvActorName, tvBriefIntro;

        public ActorViewHolder(View itemView) {
            super(itemView);

            ivActorPhoto = (ImageView) itemView.findViewById(R.id.ivActorPhoto);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvActorName = (TextView) itemView.findViewById(R.id.tvActorName);
            tvBriefIntro = (TextView) itemView.findViewById(R.id.tvBriefIntro);

        }
    }
}
