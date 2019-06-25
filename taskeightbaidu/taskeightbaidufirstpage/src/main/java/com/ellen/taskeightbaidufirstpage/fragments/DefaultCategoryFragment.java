package com.ellen.taskeightbaidufirstpage.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.taskeightbaidufirstpage.R;
import com.ellen.taskeightbaidufirstpage.adapters.DefaultCatogeryAdapter;
import com.ellen.taskeightbaidufirstpage.models.CatogeryDatum;


/**
 * Created by ellen on 15/12/8.
 */
public class DefaultCategoryFragment extends Fragment {

    private CatogeryDatum[] catogeryDatum;                  //Data
    private RecyclerView rvCatoery;                         //recyclerview
    private DefaultCatogeryAdapter defaultCatogeryAdapter;  //adapter
    private RecyclerView.LayoutManager layoutManager;       //layout

    public DefaultCategoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatum();

    }

    private void initDatum() {

        catogeryDatum = new CatogeryDatum[]{

                new CatogeryDatum("Dinner", R.drawable.wallet_home_o2o_fuma, "Eat Together"),
                new CatogeryDatum("Market", R.drawable.wallet_home_o2o_fuma, ""),
                new CatogeryDatum("Fruit", R.drawable.wallet_home_o2o_fuma, ""),
                new CatogeryDatum("Tea Time", R.drawable.wallet_home_o2o_fuma, "Eat Together"),
                new CatogeryDatum("Quality Life", R.drawable.wallet_home_o2o_fuma, ""),
                new CatogeryDatum("Me Delivery", R.drawable.wallet_home_o2o_fuma, ""),
                new CatogeryDatum("Drug Home", R.drawable.wallet_home_o2o_fuma, ""),
                new CatogeryDatum("New Shop", R.drawable.wallet_home_o2o_fuma, "Home Stay")
//                new CatogeryDatum("Brand",R.drawable.wallet_home_o2o_fuma,""),
//                new CatogeryDatum("Flowers",R.drawable.wallet_home_o2o_fuma,""),
//                new CatogeryDatum("NightNight",R.drawable.wallet_home_o2o_fuma,""),
//                new CatogeryDatum("Dinner",R.drawable.wallet_home_o2o_fuma,"Double 12")

//                Todo: make it to flip , can be 8 more category
        };


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_default_category, container, false);

        rvCatoery = (RecyclerView) view.findViewById(R.id.rvDefaultCategory);

        rvCatoery.setHasFixedSize(true);

        defaultCatogeryAdapter = new DefaultCatogeryAdapter(catogeryDatum);

        rvCatoery.setAdapter(defaultCatogeryAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getActivity(), 4,
                LinearLayoutManager.VERTICAL,
                false);

        rvCatoery.setLayoutManager(gridLayoutManager);

        return view;
    }

}
