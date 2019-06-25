package com.ellen.taskeightbaidufirstpage.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.taskeightbaidufirstpage.R;
import com.ellen.taskeightbaidufirstpage.adapters.ShopsAdapter;
import com.ellen.taskeightbaidufirstpage.models.ShopsInfoDatum;
import com.ellen.taskeightbaidufirstpage.util.DividerItemDecoration;


/**
 * Created by ellen on 15/12/8.
 */
public class DefaultShopsFragment extends Fragment {

    private ShopsInfoDatum[] shopsInfoDatums;
    private RecyclerView rvShops;
    private ShopsAdapter shopsAdapter;
    private LinearLayoutManager layoutManager;       //layout

    public DefaultShopsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatum();

    }

    private void initDatum() {

        shopsInfoDatums = new ShopsInfoDatum[]{
                new ShopsInfoDatum("YOSHINOYA", "", "29 per Month", 9,
                        new String[]{"Over $50", "Fee $8", "About 1 hour"},
                        R.drawable.jiyejia,
                        true, true, true, true),
                new ShopsInfoDatum("KFC", "", "9870 per Month", 4,
                        new String[]{"Over $30", "Fee $8", "About 1 hour"},
                        R.drawable.kedeji,
                        false, false, false, false),
                new ShopsInfoDatum("Macdonals", "Chaoyang Districts", "1345 per Month", 6,
                        new String[]{"Over $28", "Fee $8", "About 3 mins"},
                        R.drawable.maidanglao,
                        false, true, false, false),
                new ShopsInfoDatum("Zhengongfu", "", "29 per Month", 8,
                        new String[]{"Over $100", "Fee $8", "About 40 mins"},
                        R.drawable.zhengongfu,
                        false, false, false, false),
                new ShopsInfoDatum("Subway", "Beijing University", "29 per Month", 10,
                        new String[]{"Over $59", "Fee $8", "About 15 mins"},
                        R.drawable.subway,
                        true, false, true, false),
                new ShopsInfoDatum("BurgerKing", "LiuDaoKou area", "229 per Month", 3,
                        new String[]{"Over $59", "Fee $8", "About 15 mins"},
                        R.drawable.burgerking,
                        true, false, true, true)

        };


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_default_shops, container, false);

        rvShops = (RecyclerView) view.findViewById(R.id.rvDefaultShops);

        shopsAdapter = new ShopsAdapter(shopsInfoDatums);

        rvShops.setAdapter(shopsAdapter);

        layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, true);

        rvShops.setLayoutManager(layoutManager);
        rvShops.setItemAnimator(new DefaultItemAnimator());

        rvShops.addItemDecoration(new DividerItemDecoration(this.getActivity(), null, true, true));

        return view;
    }

}
