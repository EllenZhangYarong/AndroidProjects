package com.ellen.taskeightbaidu.fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ellen.taskeightbaidu.adapter.AboutMeAdapter;
import com.ellen.taskeightbaidu.model.AboutMeDatum;
import com.ellen.taskeightbaidu.R;
import com.ellen.taskeightbaidu.util.DividerItemDecoration;

/**
 * Created by ellen on 15/12/4.
 */
public class AboutMeFragment extends Fragment {

    private RecyclerView recyclerView;

    public AboutMeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about_me, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recylerViewForData);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        AboutMeDatum[] aboutMeDatum= {
                new AboutMeDatum("My Address",R.drawable.mypage_list_icon_location ),
                new AboutMeDatum("My coupon",R.drawable.mypage_list_icon_daijinjuan ),
                new AboutMeDatum("My balance",R.drawable.my_balance_icon ),
                new AboutMeDatum("My refunds",R.drawable.mypage_list_icon_refund ),
                new AboutMeDatum("",0),

                new AboutMeDatum("My message",R.drawable.my_messages ),
                new AboutMeDatum("Online service",R.drawable.mypage_list_icon_star ),
                new AboutMeDatum("",0),

                new AboutMeDatum("My store",R.drawable.mypage_list_icon_star ),
                new AboutMeDatum("My comments",R.drawable.mypage_list_icon_comment ),
                new AboutMeDatum("",0),

                new AboutMeDatum("Baidu Wallet",R.drawable.mypage_list_icon_daijinjuan ),
                new AboutMeDatum("Baidu Nuomi",R.drawable.my_balance_icon ),

                new AboutMeDatum("",0),
                new AboutMeDatum("Common Problem",R.drawable.my_messages ),
                new AboutMeDatum("Business Man",R.drawable.mypage_list_icon_star ),
                new AboutMeDatum("",0),

                new AboutMeDatum("Dial 400-011-7777",R.drawable.mypage_list_icon_call )};

        recyclerView.setAdapter(new AboutMeAdapter(aboutMeDatum));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), null, false, true));

        return root;
    }

}


