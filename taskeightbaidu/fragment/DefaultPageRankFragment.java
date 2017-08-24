package com.ellen.taskeightbaidu.fragment;

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

import com.ellen.taskeightbaidu.R;
import com.ellen.taskeightbaidu.adapter.DefaultPageRankAdapter;
import com.ellen.taskeightbaidu.model.DefaultPageCatogeryDatum;

/**
 * Created by ellen on 15/12/3.
 */
public class DefaultPageRankFragment extends Fragment {


    public DefaultPageRankFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_default_rank, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rvForRank);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));

        DefaultPageCatogeryDatum[] defaultPageCatogeryDatums = {
                new DefaultPageCatogeryDatum("热销菜品", "大家都在吃", R.drawable.waimai_launcher),
                new DefaultPageCatogeryDatum("感冒别怕", "药品满38减5", R.drawable.waimai_launcher),
                new DefaultPageCatogeryDatum("万万没想到", "代金券能叠加", R.drawable.waimai_launcher)};

        System.out.println("DefaultPageRankFragment----" +
                defaultPageCatogeryDatums[0].getCatogery() + "<---->"+
                defaultPageCatogeryDatums[0].getHint());

        recyclerView.setAdapter(new DefaultPageRankAdapter(defaultPageCatogeryDatums));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }
}
