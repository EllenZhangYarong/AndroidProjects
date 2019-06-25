package com.ellen.taskeightbaidu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ellen.taskeightbaidu.fragment.AboutMeFragment;
import com.ellen.taskeightbaidu.fragment.DefaultFragment;
import com.ellen.taskeightbaidu.fragment.DefaultPageCatogeryFragment;
import com.ellen.taskeightbaidu.fragment.OrdersFragment;
import com.ellen.taskeightbaidu.fragment.RankFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch (position) {
            case 0:
                return new DefaultFragment();
            case 1:
                return new RankFragment();
            case 2:
                return new OrdersFragment();
            case 3:
                return new AboutMeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "首页";
            case 1:
                return "吃啥";
            case 2:
                return "订单";
            case 3:
                return "我的";
        }
        return null;
    }

//    public int[] setIconSelected(int position) {
//
//        int[] iconSelectId0 = new int[]{R.drawable.tab_icon_home_selected,
//                R.drawable.rank_tab_icon_normal, R.drawable.tab_icon_dingdan,
//                R.drawable.tab_icon_me};
//        int[] iconSelectId1 = new int[]{R.drawable.tab_icon_home, R.drawable.rank_tab_icon_selected,
//                R.drawable.tab_icon_dingdan, R.drawable.tab_icon_me};
//
//        int[] iconSelectId2 = new int[]{R.drawable.tab_icon_home, R.drawable.rank_tab_icon_normal,
//                R.drawable.tab_icon_dingdan_selected, R.drawable.tab_icon_me};
//
//        int[] iconSelectId3 = new int[]{R.drawable.tab_icon_home, R.drawable.rank_tab_icon_normal,
//                R.drawable.tab_icon_dingdan, R.drawable.tab_icon_me_selected};
//
//        switch (position){
//            case 0:
//                return iconSelectId0;
//            case 1:
//                return iconSelectId1;
//            case 2:
//                return iconSelectId2;
//            case 3:
//                return iconSelectId3;
//        }
//        return null;
//    }

}
