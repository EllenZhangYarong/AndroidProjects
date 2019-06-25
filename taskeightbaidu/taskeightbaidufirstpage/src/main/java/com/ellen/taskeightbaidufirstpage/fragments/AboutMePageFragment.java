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
import com.ellen.taskeightbaidufirstpage.adapters.AboutMeAdapter;
import com.ellen.taskeightbaidufirstpage.models.AboutMeDatum;
import com.ellen.taskeightbaidufirstpage.util.DividerItemDecoration;
import com.ellen.taskeightbaidufirstpage.util.SimpleSectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ellen on 15/12/8.
 */
public class AboutMePageFragment extends Fragment {


    protected AboutMeDatum[] aboutMeDatum;
    protected RecyclerView.LayoutManager layoutManager;
    protected AboutMeAdapter aboutMeAdapter;
    private RecyclerView recyclerView;

    public AboutMePageFragment() {


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatum();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_aboutme_page, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvAboutMeFirst);

//        System.out.println(">>>>>>>>>>>>>>>>>recyclerView " + recyclerView);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        aboutMeAdapter = new AboutMeAdapter(aboutMeDatum);

        recyclerView.setAdapter(aboutMeAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), null, false, true));

        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0, "Section 1"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(4, "Section 2"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(8, "Section 3"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(10, "Section 4"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(12, "Section 5"));

        SimpleSectionedRecyclerViewAdapter.Section[] rvSections =
                new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter simpleSectionedAdapter =
                new SimpleSectionedRecyclerViewAdapter(
                        getActivity(),
                        R.layout.layout_aboutme_section,
                        R.id.tvAboutMeSection,
                        aboutMeAdapter);
        simpleSectionedAdapter.setSections(sections.toArray(rvSections));
        recyclerView.setAdapter(simpleSectionedAdapter);

        return rootView;
    }

    private void initDatum() {

        aboutMeDatum = new AboutMeDatum[]{

                new AboutMeDatum("My Address", R.drawable.mypage_list_icon_location),
                new AboutMeDatum("My coupon", R.drawable.mypage_list_icon_daijinjuan),
                new AboutMeDatum("My balance", R.drawable.my_balance_icon),
                new AboutMeDatum("My refunds", R.drawable.mypage_list_icon_refund),

                new AboutMeDatum("My message", R.drawable.my_messages),
                new AboutMeDatum("Online service", R.drawable.mypage_list_icon_star),

                new AboutMeDatum("My store", R.drawable.mypage_list_icon_star),
                new AboutMeDatum("My comments", R.drawable.mypage_list_icon_comment),

                new AboutMeDatum("Baidu Wallet", R.drawable.mypage_list_icon_daijinjuan),
                new AboutMeDatum("Baidu Nuomi", R.drawable.my_balance_icon),

                new AboutMeDatum("Common Problem", R.drawable.my_messages),
                new AboutMeDatum("Business Man", R.drawable.mypage_list_icon_star),

                new AboutMeDatum("Dial 400-011-7777", R.drawable.mypage_list_icon_call)

        };
    }
}
