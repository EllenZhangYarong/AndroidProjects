package com.ellen.taskeightbaidu.fragment;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.taskeightbaidu.adapter.DefaultPageCatogeryAdapter;
import com.ellen.taskeightbaidu.model.DefaultPageCatogeryDatum;
import com.ellen.taskeightbaidu.R;

/**
 * Created by ellen on 15/12/3.
 */
public class DefaultPageCatogeryFragment extends Fragment{


    public DefaultPageCatogeryFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_default_catogery,container,false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recylerViewForData);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,LinearLayoutManager.HORIZONTAL,false));

        DefaultPageCatogeryDatum[] defaultPageCatogeryDatums= {
                new DefaultPageCatogeryDatum("","餐饮",R.drawable.waimai_launcher ),
                new DefaultPageCatogeryDatum("","超市购",R.drawable.waimai_launcher ),
                new DefaultPageCatogeryDatum("Let's Home","水果生鲜",R.drawable.waimai_launcher ),
                new DefaultPageCatogeryDatum("","下午茶",R.drawable.waimai_launcher ),
                new DefaultPageCatogeryDatum("","地豪特供",R.drawable.waimai_launcher ),
                new DefaultPageCatogeryDatum("","质享生活",R.drawable.waimai_launcher ),
                new DefaultPageCatogeryDatum("Only Rose","百度配送",R.drawable.waimai_launcher ),
                new DefaultPageCatogeryDatum("","送药上门",R.drawable.waimai_launcher )};

        recyclerView.setAdapter(new DefaultPageCatogeryAdapter(defaultPageCatogeryDatums));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }
}
