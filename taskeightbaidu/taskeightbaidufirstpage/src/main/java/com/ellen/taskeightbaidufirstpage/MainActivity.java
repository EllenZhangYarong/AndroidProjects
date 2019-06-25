package com.ellen.taskeightbaidufirstpage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ellen.taskeightbaidufirstpage.fragments.AboutMePageFragment;
import com.ellen.taskeightbaidufirstpage.fragments.DefaultPageFragment;
import com.ellen.taskeightbaidufirstpage.fragments.OrdersPageFragment;
import com.ellen.taskeightbaidufirstpage.fragments.RankPageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //members
    private ViewPager mViewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> listFragment;

    //Four Tabs
    private LinearLayout tabHomePage, tabRankPage, tabOrdersPage, tabAboutMePage;

    //Four ImageViews
    private ImageView ivTabHomePage, ivTabRankPage, ivTabOrdersPage, ivTabAboutMePage;

    //Four TextViews
    private TextView tvTabHomePage, tvTabRankPage, tvTabOrdersPage, tvTabAboutMePage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
        initEvent();

        setSelected(0);

    }


    private void initEvent() {
        tabHomePage.setOnClickListener(this);
        tabRankPage.setOnClickListener(this);
        tabOrdersPage.setOnClickListener(this);
        tabAboutMePage.setOnClickListener(this);
    }

    //initiate the view component
    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        tabHomePage = (LinearLayout) findViewById(R.id.tabHomePage);
        tabRankPage = (LinearLayout) findViewById(R.id.tabRankPage);
        tabOrdersPage = (LinearLayout) findViewById(R.id.tabOrdersPage);
        tabAboutMePage = (LinearLayout) findViewById(R.id.tabAboutMePage);

        ivTabHomePage = (ImageView) findViewById(R.id.ivTabHomePage);
        ivTabRankPage = (ImageView) findViewById(R.id.ivTabRankPage);
        ivTabOrdersPage = (ImageView) findViewById(R.id.ivTabOrdersPage);
        ivTabAboutMePage = (ImageView) findViewById(R.id.ivTabAboutMePage);

        tvTabHomePage = (TextView) findViewById(R.id.tvTabHomePage);
        tvTabRankPage = (TextView) findViewById(R.id.tvTabRankPage);
        tvTabOrdersPage = (TextView) findViewById(R.id.tvTabOrdersPage);
        tvTabAboutMePage = (TextView) findViewById(R.id.tvTabAboutMePage);


        listFragment = new ArrayList<Fragment>();

        Fragment defaultPageFragment = new DefaultPageFragment();
        Fragment rankPageFragment = new RankPageFragment();
        Fragment ordersPageFragment = new OrdersPageFragment();
        Fragment aboutMePageFragment = new AboutMePageFragment();

        listFragment.add(defaultPageFragment);
        listFragment.add(rankPageFragment);
        listFragment.add(ordersPageFragment);
        listFragment.add(aboutMePageFragment);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }
        };

        mViewPager.setAdapter(fragmentPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        resetState();
        switch (v.getId()) {
            case R.id.tabHomePage:
                setSelected(0);
                break;
            case R.id.tabRankPage:
                setSelected(1);
                break;
            case R.id.tabOrdersPage:
                setSelected(2);
                break;
            case R.id.tabAboutMePage:
                setSelected(3);
                break;
        }

    }

    private void setSelected(int position) {

        setTab(position);
        mViewPager.setCurrentItem(position);
    }

    private void setTab(int position) {

        //First, initiate all tab to uncliced state
        //Second, set clicked state for tab which is clicked

        resetState();

        switch (position) {
            case 0:
                ivTabHomePage.setImageResource(R.drawable.tab_icon_home_selected);
                tvTabHomePage.setTextColor(Color.RED);
                break;
            case 1:
                ivTabRankPage.setImageResource(R.drawable.rank_tab_icon_selected);
                tvTabRankPage.setTextColor(Color.RED);
                break;
            case 2:
                ivTabOrdersPage.setImageResource(R.drawable.tab_icon_dingdan_selected);
                tvTabOrdersPage.setTextColor(Color.RED);
                break;
            case 3:
                ivTabAboutMePage.setImageResource(R.drawable.tab_icon_me_selected);
                tvTabAboutMePage.setTextColor(Color.RED);
                break;
        }
    }

    private void resetState() {

        //initiate the four imageviews the img not clicked
        ivTabHomePage.setImageResource(R.drawable.tab_icon_home);
        ivTabRankPage.setImageResource(R.drawable.rank_tab_icon_normal);
        ivTabOrdersPage.setImageResource(R.drawable.tab_icon_dingdan);
        ivTabAboutMePage.setImageResource(R.drawable.tab_icon_me);

        tvTabHomePage.setTextColor(Color.GRAY);
        tvTabRankPage.setTextColor(Color.GRAY);
        tvTabOrdersPage.setTextColor(Color.GRAY);
        tvTabAboutMePage.setTextColor(Color.GRAY);
    }
}
