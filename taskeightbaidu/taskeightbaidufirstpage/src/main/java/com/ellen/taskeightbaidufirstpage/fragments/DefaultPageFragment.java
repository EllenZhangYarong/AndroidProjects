package com.ellen.taskeightbaidufirstpage.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.taskeightbaidufirstpage.R;


/**
 * Created by ellen on 15/12/10.
 */
public class DefaultPageFragment extends Fragment {


    DefaultCategoryFragment defaultCategoryFragment;
    DefaultShopsFragment defaultShopsFragment;
    FragmentTransaction fragmentTransaction;
    android.support.v4.app.FragmentManager fragmentManager;

    public DefaultPageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        defaultCategoryFragment = new DefaultCategoryFragment();
        defaultShopsFragment = new DefaultShopsFragment();
        fragmentManager = getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragCategory, defaultCategoryFragment);
        fragmentTransaction.add(R.id.fragShops, defaultShopsFragment);
        fragmentTransaction.commit();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_default_page, container, false);

        return view;
    }
}
