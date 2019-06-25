package com.ellen.taskeightbaidufirstpage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.taskeightbaidufirstpage.R;
import com.ellen.taskeightbaidufirstpage.models.ShopsInfoDatum;

/**
 * Created by ellen on 15/12/9.
 */

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder> {

    private ShopsInfoDatum[] shopsInfoDatums;

    public ShopsAdapter(ShopsInfoDatum[] shopsInfoDatums) {
        this.shopsInfoDatums = shopsInfoDatums;
    }

    @Override
    public ShopsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_default_shopcards, parent, false);

        ShopsViewHolder shopsViewHolder = new ShopsViewHolder(view);

        return shopsViewHolder;
    }

    @Override
    public void onBindViewHolder(ShopsViewHolder holder, int position) {

        initRTPC(holder);
        initRank(holder);

        ShopsInfoDatum shop = shopsInfoDatums[position];
        String shopName = "";
        if (shop.getShopBrunch() != "") {
            shopName = shop.getShopName() + "(" + shop.getShopBrunch() + ")";
        } else {
            shopName = shop.getShopName();
        }

        holder.tvDefaultCardsTitle.setText(shopName);
        holder.ivDefaultCardsImage.setImageResource(shop.getShopPicUri());
        holder.tvAmountOfSales.setText(shop.getAmountOfSold());


        if (shop.getIsCoupons()) {
            holder.tvForCoupons.setVisibility(View.VISIBLE);
            holder.tvForCoupons.setText("C");
        } else {
            holder.tvForCoupons.setHeight(0);
            holder.tvForCoupons.setWidth(0);
        }

        if (shop.getIsRefund()) {
            holder.tvForRefunds.setVisibility(View.VISIBLE);
            holder.tvForRefunds.setText("R");
        } else {
            holder.tvForRefunds.setHeight(0);
            holder.tvForRefunds.setWidth(0);
        }

        if (shop.getIsRepay()) {
            holder.tvForPay.setVisibility(View.VISIBLE);
            holder.tvForPay.setText("P");
        } else {
            holder.tvForPay.setHeight(0);
            holder.tvForPay.setWidth(0);
        }

        if (shop.getIsTicket()) {
            holder.tvForTickets.setVisibility(View.VISIBLE);
            holder.tvForTickets.setText("T");
        } else {
            holder.tvForTickets.setHeight(0);
            holder.tvForTickets.setWidth(0);
        }


        switch (shop.getShopRank()) {
            case 0:
                break;
            case 1:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_half);
                break;
            case 2:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                break;
            case 3:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_half);
                break;
            case 4:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                break;
            case 5:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank3.setImageResource(R.drawable.businesslistings_list_icon_star_half);
                break;
            case 6:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank3.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                break;
            case 7:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank3.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank4.setImageResource(R.drawable.businesslistings_list_icon_star_half);
                break;
            case 8:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank3.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank4.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                break;
            case 9:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank3.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank4.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank5.setImageResource(R.drawable.businesslistings_list_icon_star_half);
                break;
            case 10:
                holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank3.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank4.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                holder.ivRank5.setImageResource(R.drawable.businesslistings_list_icon_star_full);
                break;
            default:
                break;

        }

        String[] con = shop.getConditions();
        String cons = "";
        for (int i = 0; i < con.length; i++) {

            cons += con[i];
            if (i < con.length - 1)
                cons += " | ";
        }

        holder.tvDefaultCardsCondition.setText(cons);

    }

    private void initRank(ShopsViewHolder holder) {
        holder.ivRank1.setImageResource(R.drawable.businesslistings_list_icon_star_no);
        holder.ivRank2.setImageResource(R.drawable.businesslistings_list_icon_star_no);
        holder.ivRank3.setImageResource(R.drawable.businesslistings_list_icon_star_no);
        holder.ivRank4.setImageResource(R.drawable.businesslistings_list_icon_star_no);
        holder.ivRank5.setImageResource(R.drawable.businesslistings_list_icon_star_no);
    }

    private void initRTPC(ShopsViewHolder holder) {

        holder.tvForCoupons.setVisibility(View.INVISIBLE);
        holder.tvForPay.setVisibility(View.INVISIBLE);
        holder.tvForRefunds.setVisibility(View.INVISIBLE);
        holder.tvForTickets.setVisibility(View.INVISIBLE);
    }


    @Override
    public int getItemCount() {
        return shopsInfoDatums.length;
    }

    protected static class ShopsViewHolder extends RecyclerView.ViewHolder {

        /*
        ivDefaultCardsImage,tvDefaultCardsTitle,tvForRefunds,tvForPay,tvForTickets,tvForCoupons

        rank1~5,tvDefaultCardsCondition
             * */

        ImageView ivDefaultCardsImage, ivRank1, ivRank2, ivRank3, ivRank4, ivRank5;
        TextView tvDefaultCardsTitle, tvForRefunds, tvForPay,
                tvForTickets, tvForCoupons, tvDefaultCardsCondition, tvAmountOfSales;

        public ShopsViewHolder(View itemView) {
            super(itemView);

            ivRank1 = (ImageView) itemView.findViewById(R.id.ivRank1);
            ivRank2 = (ImageView) itemView.findViewById(R.id.ivRank2);
            ivRank3 = (ImageView) itemView.findViewById(R.id.ivRank3);
            ivRank4 = (ImageView) itemView.findViewById(R.id.ivRank4);
            ivRank5 = (ImageView) itemView.findViewById(R.id.ivRank5);

            ivDefaultCardsImage = (ImageView) itemView.findViewById(R.id.ivDefaultCardsImage);

            tvDefaultCardsTitle = (TextView) itemView.findViewById(R.id.tvDefaultCardsTitle);
            tvForRefunds = (TextView) itemView.findViewById(R.id.tvForRefunds);
            tvForPay = (TextView) itemView.findViewById(R.id.tvForPay);
            tvForTickets = (TextView) itemView.findViewById(R.id.tvForTickets);
            tvForCoupons = (TextView) itemView.findViewById(R.id.tvForCoupons);
            tvDefaultCardsCondition = (TextView) itemView.findViewById(R.id.tvDefaultCardsCondition);
            tvAmountOfSales = (TextView) itemView.findViewById(R.id.tvAmountOfSales);

        }
    }
}
